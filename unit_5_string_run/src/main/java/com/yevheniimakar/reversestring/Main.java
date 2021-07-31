package com.yevheniimakar.reversestring;

import com.yevheniimakar.reversestring.console.ConsoleReader;


public class Main {


    public static void main(String[] args) {
        boolean continueApp = true;
        ConsoleReader consoleReader = new ConsoleReader();

        while(continueApp){
            System.out.println("1 - for reverse all");
            System.out.println("2 - for reverse only word");
            System.out.println("3 - for reverse substring");
            System.out.println("4 - for reverse between index");
            System.out.println("5 - for reverse between char");
            System.out.println("6 - for reverse between substring");
            System.out.println("7 - exit");
            System.out.print("Your choice: ");
            String choice  = consoleReader.getStringFromConsole().replaceAll("[ \\t\\n\\x0B\\f\\r]","").replace("[^1-7]", "");;

            switch (Integer.parseInt(choice)){
                case 1:{
                    System.out.println("Enter string: ");
                    String string = consoleReader.getStringFromConsole();
                    boolean reversOnlyWord = false;
                    System.out.println(ReverseString.reverse(string, reversOnlyWord));
                }
                break;

                case 2:{
                    System.out.println("Enter string: ");
                    String string = consoleReader.getStringFromConsole();
                    boolean reversOnlyWord = true;
                    System.out.println(ReverseString.reverse(string, reversOnlyWord));
                }
                break;

                case 3:{
                    System.out.println("Enter string: ");
                    String string = consoleReader.getStringFromConsole();System.out.println("Enter string: ");
                    System.out.println("Enter substring: ");
                    String substring = consoleReader.getStringFromConsole();
                    System.out.println(ReverseString.reverse(string, substring, true));
                }
                break;

                case 4:{
                    System.out.println("Enter string: ");
                    String string = consoleReader.getStringFromConsole();System.out.println("Enter string: ");
                    System.out.println("Enter first index: ");
                    int firstIndex = Integer.parseInt(consoleReader.getStringFromConsole().replaceAll("[ \\t\\n\\x0B\\f\\r]","").replace("\\D", ""));
                    System.out.println("Enter last index: ");
                    int lastIndex =Integer.parseInt(consoleReader.getStringFromConsole().replaceAll("[ \\t\\n\\x0B\\f\\r]","").replace("\\D", ""));
                    System.out.println(ReverseString.reverse(string, firstIndex ,lastIndex));
                }
                break;

                case 5:{
                    System.out.println("Enter string: ");
                    String string = consoleReader.getStringFromConsole();System.out.println("Enter string: ");
                    System.out.println("Enter first index: ");
                    char firstChar = consoleReader.getStringFromConsole().charAt(0);
                    System.out.println("Enter last index: ");
                    char lastChar = consoleReader.getStringFromConsole().charAt(0);
                    System.out.println(ReverseString.reverse(string, firstChar ,lastChar));
                }
                break;

                case 6:{
                    System.out.println("Enter string: ");
                    String string = consoleReader.getStringFromConsole();System.out.println("Enter string: ");
                    System.out.println("Enter first substring: ");
                    String firstSubstring = consoleReader.getStringFromConsole();
                    System.out.println("Enter last substring: ");
                    String lastSubstring  = consoleReader.getStringFromConsole();
                    System.out.println(ReverseString.reverse(string, firstSubstring  ,lastSubstring ));
                }
                break;

                case 7:{
                    continueApp = false;

                }
                break;

            }


        }

    }

}
