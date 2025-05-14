package com.axone_io.ignition.git.utils;

import java.util.regex.Pattern;

public final class CommitMessageValidator {

    /** Conventional Commits check: <type>: <description>   */
    private static final Pattern CC_PATTERN =
            Pattern.compile(
                    "^(build|chore|ci|docs|feat|fix|perf|refactor|revert|style|test)" +  // allowed types
                    "(\\([\\w./-]+\\))?" +                                              // optional scope
                    "(!)?" +                                                            // optional breaking‑change flag
                    ":\\s.+$",                                                          // colon + space + description
                    Pattern.CASE_INSENSITIVE);

    private CommitMessageValidator() { /* util */ }

    public static boolean isValid(String msg) {
        if (msg == null) { return false; }
        // take first non‑empty line
        String headline = msg.lines()
                .filter(l -> !l.isBlank())
                .findFirst()
                .orElse("");
        return CC_PATTERN.matcher(headline).matches();
    }
}
