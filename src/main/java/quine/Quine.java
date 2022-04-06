package quine;
public class Quine {
    public static void main(String[] args) {
        char quote = 34;
        String[] code = {
                "package quine;",
                "public class Quine {",
                "    public static void main(String[] args) {",
                "        char quote = 34;",
                "        String[] code = {",
                "        };",
                "        for(int i=0; i<5; i++){",
                "            System.out.println(code[i]);",
                "        }",
                "        for(int i=0; i<code.length; i++){",
                "            System.out.println(quote + code[i] + quote + ',');",
                "        }",
                "        for(int i=5; i<code.length; i++){",
                "            System.out.println(code[i]);",
                "        }",
                "    }",
                "}",
        };
        for(int i=0; i<5; i++){
            System.out.println(code[i]);
        }
        for (String s : code) {
            System.out.println(quote + s + quote + ',');
        }
        for(int i=5; i<code.length; i++){
            System.out.println(code[i]);
        }
    }
}