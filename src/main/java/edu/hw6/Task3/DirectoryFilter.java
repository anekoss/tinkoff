package edu.hw6.Task3;

import java.io.IOException;
import java.nio.file.Files;

public class DirectoryFilter {

    private DirectoryFilter() {
    }

    public static AbstractFilter regularFile = Files::isRegularFile;
    public static AbstractFilter readable = Files::isReadable;
    public static AbstractFilter writeable = Files::isWritable;

    public static AbstractFilter sizeLargerThan(long size) {
        return (entry -> {
            try {
                return Files.size(entry) > size;
            } catch (IOException e) {
                return false;
            }
        });
    }

    public static AbstractFilter sizeLessThan(long size) {
        return (entry -> {
            try {
                return Files.size(entry) < size;
            } catch (IOException e) {
                return false;
            }
        });
    }

    public static AbstractFilter sizeEquals(long size) {
        return (entry -> {
            try {
                return Files.size(entry) == size;
            } catch (IOException e) {
                return false;
            }
        });
    }

    public static AbstractFilter globMatches(String glob) {
        return (entry -> {
            int index = entry.toString().lastIndexOf(".");
            if (glob != null && glob.matches("^[.][^.]+$") && index != -1) {
                return entry.toString().substring(index).equals(glob);
            }
            return false;
        });
    }

    public static AbstractFilter regexContains(String pattern) {
        return (entry -> pattern != null && entry.toString().matches(pattern));
    }

    public static AbstractFilter magicNumber(byte[] magicNumber) {
        return (entry -> {
            try {
                byte[] bytes = Files.readAllBytes(entry);
                if (magicNumber.length > bytes.length) {
                    return false;
                }
                for (int i = 0; i < magicNumber.length; i++) {
                    if (magicNumber[i] != bytes[i]) {
                        return false;
                    }
                }
                return true;
            } catch (IOException e) {
                return false;
            }
        });
    }
}
