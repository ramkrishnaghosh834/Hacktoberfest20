// Java program to find minimum breaks needed
// to break a string in dictionary words.
public class Trie {

TrieNode root = new TrieNode();
int minWordBreak = Integer.MAX_VALUE;

	// Trie node
	class TrieNode {
		boolean endOfTree;
		TrieNode children[] = new TrieNode[26];
		TrieNode(){
			endOfTree = false;
			for(int i=0;i<26;i++){
				children[i]=null;
			}
		}
	}

	// If not present, inserts a key into the trie
	// If the key is the prefix of trie node, just
	// marks leaf node
	void insert(String key){
		int length = key.length();

		int index;

		TrieNode pcrawl = root;

		for(int i = 0; i < length; i++)
		{
			index = key.charAt(i)- 'a';

			if(pcrawl.children[index] == null)
				pcrawl.children[index] = new TrieNode();

			pcrawl = pcrawl.children[index];
		}
		
		// mark last node as leaf
		pcrawl.endOfTree = true;

	}

	// function break the string into minimum cut
	// such the every substring after breaking 
	// in the dictionary.
	void minWordBreak(String key)
	{
		minWordBreak = Integer.MAX_VALUE;
		
		minWordBreakUtil(root, key, 0, Integer.MAX_VALUE, 0);
	}
	
	void minWordBreakUtil(TrieNode node, String key,
				int start, int min_Break, int level)
	{
		TrieNode pCrawl = node;

		// base case, update minimum Break
		if (start == key.length()) {
			min_Break = Math.min(min_Break, level - 1);
			if(min_Break<minWordBreak){
				minWordBreak = min_Break;
			}
			return;
		}

		// traverse given key(pattern)
		for (int i = start; i < key.length(); i++) {
			int index = key.charAt(i) - 'a';
			if (pCrawl.children[index]==null)
				return;

			// if we find a condition were we can
			// move to the next word in a trie
			// dictionary
			if (pCrawl.children[index].endOfTree) {
				minWordBreakUtil(root, key, i + 1,
						min_Break, level + 1);

			}
			pCrawl = pCrawl.children[index];
		}
	}

	// Driver code
	public static void main(String[] args)
	{
		String keys[] = {"cat", "mat", "ca", "ma",
					"at", "c", "dog", "og", "do" };

		Trie trie = new Trie();

		// Construct trie
		
		int i;
		for (i = 0; i < keys.length ; i++)
			trie.insert(keys[i]);
		
		trie.minWordBreak("catmatat");

		System.out.println(trie.minWordBreak);
	}
}

// This code is contributed by Pavan Koli.
