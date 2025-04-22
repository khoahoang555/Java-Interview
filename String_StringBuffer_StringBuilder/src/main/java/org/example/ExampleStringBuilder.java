/*
 * Copyright © 2025 GFIT Inc. All rights reserved.
 */
package org.example;

public class ExampleStringBuilder {

    public static void main(String[] args) {
        String status = "active";
        Integer limit = 10;

        String queryStatement = buildQueryStatement(status, limit);
        System.out.println(queryStatement);
    }

    private static String buildQueryStatement(String status, Integer limit) {
        // Should use StringBuilder when the value is going to change and run simultaneously
        StringBuilder sb = new StringBuilder("SELECT * FROM users WHERE 1=1");

        if (status != null && !status.isEmpty()) {
            sb.append(" AND status = '").append(status).append("'");
        }

        if (limit != null) {
            sb.append(" LIMIT ").append(limit);
        }

        return sb.toString();
    }
}
