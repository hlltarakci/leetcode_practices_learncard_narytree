// https://leetcode.com/explore/learn/card/n-ary-tree/130/traversal/925/

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
    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        preorderIter(root, list);
        return list;
    }
    
    private void preorderIter(Node root, List<Integer> list) {
        if(root == null) return;
        
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            Node curr = stack.pop();
            list.add(curr.val);
            for(int i=curr.children.size()-1; i >= 0; i--) stack.push(curr.children.get(i));
        }
    }
    
    private void preorderRec(Node root, List<Integer> list) {
        if(root == null) return;
        list.add(root.val);
        for(Node child: root.children) preorderRec(child, list);
    }
}
