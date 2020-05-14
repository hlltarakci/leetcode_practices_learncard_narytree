// https://leetcode.com/explore/learn/card/n-ary-tree/130/traversal/926/

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
    public List<Integer> postorder(Node root) {
        List<Integer> list = new ArrayList<>();
        postorderIter(root, list);
        return list;
    }
    
    private void postorderIter(Node root, List<Integer> list) {
        if(root == null) return;
        Stack<Node> stack = new Stack<>();
        Set<Node> seen = new HashSet<>();
        
        stack.add(root);
        while(!stack.isEmpty()) {
            Node curr = stack.peek();
            
            if(curr.children.size() == 0) {
                stack.pop();
                list.add(curr.val);
                seen.add(curr);
            } else {
                boolean allChildrenSeen = true;
                for(int i=curr.children.size()-1; i>=0; i--) {
                    if(!seen.contains(curr.children.get(i))) {
                        stack.push(curr.children.get(i));
                        allChildrenSeen = false;
                    }
                }
                if(allChildrenSeen) {
                    stack.pop();
                    list.add(curr.val);
                    seen.add(curr);
                }
            }
        }
    }
    
    private void postorderRec(Node root, List<Integer> list) {
        if(root == null) return ;
        for(Node child: root.children) postorderRec(child, list);
        list.add(root.val);
    }
}
