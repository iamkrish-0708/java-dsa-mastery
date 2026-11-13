package leetcode;

// LeetCode #2114 - Maximum Number of Words Found in Sentences
// Difficulty: Easy
// Problem: A sentence is a list of words that are separated by a single space with no leading
//          or trailing spaces. You are given an array of strings sentences, where each sentences[i]
//          represents a single sentence. Return the maximum number of words that appear in a single sentence.
//
// =================================================================================================
// 💡 TECHNIQUE: O(1) AUXILIARY SPACE CHAR-BY-CHAR SPACE COUNTING (4 ms ⚡)
// =================================================================================================
// - Core Insight: A sentence with S spaces contains exactly S + 1 words.
// - Why avoid split(" "):
//     * sentence.split(" ") allocates an array of String objects on the heap for every sentence,
//       incurring regex parsing overhead and garbage collection pressure.
//     * Counting ' ' characters in a single linear pass uses strictly O(1) auxiliary space!
//
// - Approaches:
//     * Approach 1: Krish's Initial While-Loop space counter (4 ms).
//     * Approach 2: Idiomatic Enhanced For-Loop with auto-scoped counter (4 ms).
//
// Complexity:
//   - Time Complexity: O(Total Characters) — Single linear pass across all sentence strings.
//   - Space Complexity: O(1) Auxiliary Space — Zero heap object allocations.
// =================================================================================================

public class LC2114_MaximumNumberOfWordsFoundInSentences {

    // Approach 2: Clean Idiomatic Enhanced For-Loop (4 ms)
    public int mostWordsFound(String[] sentences) {
        int max = 0;
        for (String s : sentences) {
            int count = 1;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ' ') {
                    count++;
                }
            }
            max = Math.max(max, count);
        }
        return max;
    }

    // Approach 1: Krish's Initial While-Loop Space Counter (4 ms)
    public int mostWordsFoundInitial(String[] sentences) {
        int count = 1;
        int max = 1;
        for (int i = 0; i < sentences.length; i++) {
            String str = sentences[i];
            int x = 0;
            while (x < str.length()) {
                if (str.charAt(x) == ' ') {
                    count++;
                }
                x++;
            }
            max = Math.max(max, count);
            count = 1;
        }
        return max;
    }

    public static void main(String[] args) {
        LC2114_MaximumNumberOfWordsFoundInSentences solver = new LC2114_MaximumNumberOfWordsFoundInSentences();

        String[] s1 = {"alice and bob love leetcode", "i think so too", "this is great thanks very much"};
        System.out.println("Test 1 Expected: 6 | Output: " + solver.mostWordsFound(s1));

        String[] s2 = {"please wait", "continue to fight", "continue to win"};
        System.out.println("Test 2 Expected: 3 | Output: " + solver.mostWordsFound(s2));
    }
}
