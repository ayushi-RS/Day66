class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>();
        Set<String> visited = new HashSet<>();
        for (String s: wordList) {
            set.add(s);
        }
        if (!set.contains(endWord)) return 0;
        if (set.contains(beginWord)) set.remove(beginWord);
        visited.add(beginWord);
        visited.add(endWord);
        Set<String> s1 = new HashSet<>();
        Set<String> s2 = new HashSet<>();
        s1.add(beginWord);
        s2.add(endWord);
        int step = 0;
        while(!s1.isEmpty() && !s2.isEmpty()) {
            step++;
			// make sure the sizes are weighted
            if(s1.size() > s2.size()) {
                Set<String> temp = s1;
                s1 = s2;
                s2 = temp;
            }
            Set<String> s3 = new HashSet<>();
            for (String c: s1) {
                char[] cArray = c.toCharArray();
                for (int j = 0; j < cArray.length; j++) {
                    char temp = cArray[j];
                    for (int k = 'a'; k <= 'z'; k++) {                       
                        if (k != temp) {
                           cArray[j] = (char)k; 
                        }         
                        String x = String.valueOf(cArray);                        
                        if (s2.contains(x)) return step + 1;
                        if (!set.contains(x)) continue;      
                        if (visited.contains(x)) continue;
                        s3.add(x);       
                        visited.add(x);
                    }
                    cArray[j] = temp;
                }   
            }
            
            s1 = s3;
        }
        return 0;
    }
}