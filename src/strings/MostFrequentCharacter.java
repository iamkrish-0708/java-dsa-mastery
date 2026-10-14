package strings;

public class MostFrequentCharacter {
    public static void main(String[] args) {
        String s="leetcode";
        int[] charArr=new int[26];
        // IN JAVA CHARACTER HAVE UNICODE VALUES a=97 , b=98 , c=99
        /*
            currChar - 'a' -> THIS CONVERTS CHARACTERS INTO APPROPRIATE INDEX IN DECLARED ARRAY
            EX- currChar = 'c' :- 'c' - 'a' == 99 - 97 = 2
                HENCE charArr[2]++; REPRESENTS INCREASE THE FREQUENCY OF CHARACTER c IN DECLARED ARRAY KEEPING RECORD OF CHARACTER FREQUENCY

            (char)( j + 'a') -> THIS CONVERTS INT INTO A CHARACTER
            EX - j=2 , SO 2 + 97 = 99 TYPE CONVERSION INTO CHAR TYPE RESULTS 'c'
        */
        for(int i=0;i<s.length();i++){
            char currChar=s.charAt(i);
            charArr[currChar - 'a']++;
        }
        char ans='a';
        int maxFreq=-1;
        for(int j=0;j<26;j++){
            if(charArr[j]>maxFreq){
                maxFreq=charArr[j];
                ans=(char)(j+'a');
            }
        }
        System.out.println(ans);
    }
}
