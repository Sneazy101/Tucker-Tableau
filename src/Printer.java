class Printer{

    public void print(float[][] tab, String[] vars, String[] slack, int m, int n) {
        if (vars == null || slack == null) {
            // Fallback names if you built with the (m, n) constructor
            vars  = new String[n];
            slack = new String[m];
            for (int j = 0; j < n; j++) vars[j] = "X" + (j + 1);
            for (int i = 0; i < m; i++) slack[i] = "t" + (i + 1);
            if (m > 0) slack[m - 1] = " f";
        }

        // 1) Pre-format numbers and compute column widths
        String[][] cells = new String[m][n];
        int[] colWidth = new int[n];
        for (int j = 0; j < n; j++) {
            colWidth[j] = vars[j].length(); // start with header width
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                cells[i][j] = fmt(tab[i][j]); // formatted number
                colWidth[j] = Math.max(colWidth[j], cells[i][j].length());
            }
        }
        // Add a little padding
        for (int j = 0; j < n; j++) colWidth[j] += 2;

        int slackWidth = "Basic".length();
        for (int i = 0; i < m; i++) slackWidth = Math.max(slackWidth, slack[i].length());
        slackWidth += 2;

        // 2) Separator line
        String sep = makeSeparator(colWidth, slackWidth);

        // 3) Header
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < n; j++) {
            sb.append(pad(vars[j], colWidth[j]));
        }
        sb.append("| ").append(pad("Basic", slackWidth - 2));
        System.out.println(sb);
        System.out.println(sep);

        // 4) Rows
        for (int i = 0; i < m; i++) {
            sb.setLength(0);
            for (int j = 0; j < n; j++) {
                sb.append(pad(cells[i][j], colWidth[j]));
            }
            sb.append("| ").append(pad(slack[i], slackWidth - 2));
            System.out.println(sb);
        }
        System.out.println(sep);
    }

// --- helpers ---

    // Format numbers nicely: 4 decimals, trim trailing zeros and the dot, collapse tiny -0's to 0
    private static String fmt(float v) {
        if (Math.abs(v) < 1e-7f) v = 0f; // avoid -0.0000
        String s = String.format(java.util.Locale.US, "%.4f", v);
        // trim trailing zeros and optional dot
        s = s.indexOf('.') >= 0 ? s.replaceAll("0+$", "").replaceAll("\\.$", "") : s;
        return s;
    }

    // Pad right to a fixed width
    private static String pad(String s, int width) {
        StringBuilder b = new StringBuilder(s);
        while (b.length() < width) b.append(' ');
        return b.toString();
    }

    private static String makeSeparator(int[] colWidth, int slackWidth) {
        int total = 0;
        for (int w : colWidth) total += w;
        total += 2 + slackWidth; // " | " + slack column
        StringBuilder line = new StringBuilder(total);
        for (int i = 0; i < total; i++) line.append('-');
        return line.toString();
    }

}