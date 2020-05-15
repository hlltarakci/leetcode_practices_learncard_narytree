// https://leetcode.com/explore/learn/card/n-ary-tree/131/recursion/919/

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    /*
        n: num of nodes
        h: height of tree
        time: O(n)
        space: O(h)
    */
    public int maxDepth(Node root) {
        if(root == null) return 0;
        
        int maxChildDepth = 0;
        for(Node child: root.children) {
            maxChildDepth = Math.max(maxChildDepth, maxDepth(child));
        }
        
        return maxChildDepth + 1;
    }
}
