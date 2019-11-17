package com.capulus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;

public class Anagram {

    public static void main(String[] args) {
        try {
            String inputString = "10\n" +
                    "tttttttttttttttttttttttttttttttttttttsssssssssssssssss\n" +
                    "sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss\n" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa\n" +
                    "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb\n" +
                    "gsadasfdashdasdkjgadhjdyjhasfdasgd\n" +
                    "ajdfaghfsdasgdfagsdhgasdfasfdahd\n" +
                    "ahjjjjjjjjjjjjjjjjjjjjjjsdgajfhsdhafshdfasjdsahfdasd\n" +
                    "aksuhdgyhjagsdjhasdkjasldashdlashdjas\n" +
                    "aiustdiagsdiiiiiiiiiyasdhkagdyafdfasdfausda\n" +
                    "ajkgdiuagdiyafdiayfdkafdkadfad\n" +
                    "aaskjdgiuagdalidiaudghasugd\n" +
                    "ausigtduasgdaksdgasldgbslgdisudgs\n" +
                    "ajhdasnhccniuadgasfdgsajasdvasdvasdas\n" +
                    "alfdlhasdlasgdjasgdlasdgiyasfdsadasd\n" +
                    "akusdiluasdasvdihasidasjldsadas\n" +
                    "asgdiuasgdiuavdlavdasdvsdlvsulyd\n" +
                    "jhghdjhsgfjhsvfjdsvfjhsvfjvsdfsd\n" +
                    "slfgyedgflsdgfilydsgfipgdsipfgdsfgd\n" +
                    "sduifiusdgfiulsdgfigsdilfuseteygyewrfwurw\n" +
                    "lwuigrywfdlfsdfisegfsdgfs";
            BufferedReader in = new BufferedReader(new StringReader(inputString));
            //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            int numberOfTestCase = Integer.parseInt(in.readLine());
            char[] charArray1, charArray2, temp;
            char dummyChar = '@';
            int charArray1Length, charArray2Length, tempCharArray1LengthStore, itemToDelete;
            boolean charNotFound = true;

            for (int i = 0; i < numberOfTestCase; i++) {
                charArray1 = in.readLine().toCharArray();
                charArray2 = in.readLine().toCharArray();
                itemToDelete = 0;

                charArray1Length = charArray1.length;
                charArray2Length = charArray2.length;

                if (charArray2Length > charArray1Length) {
                    temp = charArray1;
                    charArray1 = charArray2;
                    charArray2 = temp;

                    // swap length also
                    tempCharArray1LengthStore = charArray1Length;
                    charArray1Length = charArray2Length;
                    charArray2Length = tempCharArray1LengthStore;
                }

                tempCharArray1LengthStore = charArray1Length;

                // now charArray1 & charArrayLength1 will always have the biggest array
                for (int j = 0; j < charArray2Length; j++) {
                    charNotFound = true;

                    for (int k = 0; k < charArray1Length; k++) {
                        // find if the first array has elements
                        if (charArray2[j] == charArray1[k]) {
                            charArray1[k] = dummyChar;
                            charNotFound = false;
                            tempCharArray1LengthStore--;
                            break;
                        }
                    }

                    if (charNotFound) {
                        itemToDelete++;
                    }


                }

                /*System.out.println("charArray1Length = " + charArray1Length);
                System.out.println("charArray2Length = " + charArray2Length);

                System.out.println("Anagram:main: tempCharArray1LengthStore " + tempCharArray1LengthStore);
                System.out.println("Anagram:main: itemToDelete = " + itemToDelete);

                System.out.println("Anagram:main: " + new String(charArray1));
                System.out.println("Anagram:main: " + new String(charArray2));*/

                System.out.println((itemToDelete + tempCharArray1LengthStore));
            }

            charArray1 = null;
            charArray2 = null;
            temp = null;


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
