public class Encryption {
    private final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private final String encryptAlphabet = "qwertyuiopasdfghjklzxcvbnm";
    

    public Encryption() {
    }

    public String encrypt(String Text, int shiftKey, char space) {
        String cipherText = "";
        int charPosition=0;
        for (int i = 0; i < Text.length(); i++) {
            char currentChar = Text.charAt(i);
            if(currentChar == '('){
                cipherText += "(";
                String reverse = "";
                while(true){
                    i++;
                    currentChar = Text.charAt(i);
                    if(currentChar == ')') {
                        cipherText += reverseString(reverse) + currentChar;
                        break;
                    }
                    reverse += encrypt(Character.toString(currentChar), shiftKey, space);
                }
                continue;
            }
            else if (Character.isUpperCase(currentChar)) {
                cipherText += "^";
                charPosition = alphabet.indexOf(Character.toLowerCase(currentChar));
            }
            else if(currentChar == ' '){
                cipherText += space;
                continue;
            }
            else if(!alphabet.contains(Character.toString(currentChar))){
                cipherText += currentChar;
                continue;
            }
            else {
                charPosition = alphabet.indexOf(currentChar);
            }
            int keyVal = (charPosition + shiftKey) % 26;
            char replaceVal = encryptAlphabet.charAt(keyVal);
            cipherText += replaceVal;
        }
        return cipherText;
    }

    public String decrypt(String input, int shiftKey, char space) {
        String cipherText = "";
        int charPosition=0, keyVal;
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if(currentChar == '('){
                String reverse = "";
                while(true){
                    i++;
                    currentChar = input.charAt(i);
                    if(currentChar == ')') {
                        cipherText += decrypt(reverseString(reverse), shiftKey, space);
                        break;
                    }
                    reverse += currentChar;
                }
            }
            else if (currentChar == '^') {
                currentChar = input.charAt(i+1);
                i++;
                charPosition = encryptAlphabet.indexOf(currentChar);
                if(charPosition < shiftKey)
                    keyVal = (26 + charPosition - shiftKey) % 26;
                else
                    keyVal = (charPosition - shiftKey) % 26;

                char replaceChar = alphabet.charAt(keyVal);
                cipherText += Character.toUpperCase(replaceChar);
            }
            else if(currentChar == space){
                cipherText += " ";
            }
            else if(!encryptAlphabet.contains(Character.toString(currentChar))){
                cipherText += currentChar;
            }
            else {
                charPosition = encryptAlphabet.indexOf(currentChar);
                if(charPosition < shiftKey)
                    keyVal = (26 + charPosition - shiftKey) % 26;
                else
                    keyVal = (charPosition - shiftKey) % 26;

                char replaceChar = alphabet.charAt(keyVal);
                cipherText += replaceChar;
            }
        }
        return cipherText;
    }

    public String reverseString(String text){
        String reversedStr = "";
        
        for (int i = 0; i < text.length(); i++) {
            reversedStr = text.charAt(i) + reversedStr;
        }
        
        return reversedStr;
    }
    public static void main(String[] args) {
        Encryption encryption = new Encryption();
        //Pang Tong sent to us the encrypted input
        String encryptText = encryption.encrypt("Advise Cao Cao to use The Chain Strategem, which is to chain his battleships with strong iron chains.", 7, '$');
        System.out.println("Encrypted Text = " + encryptText);
        //We decrypt the input
        String decryptText = encryption.decrypt(encryptText, 7, '$');
        System.out.println("Decrypted Text = "+ decryptText);
}
}