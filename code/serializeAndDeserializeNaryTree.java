// https://leetcode.com/explore/learn/card/n-ary-tree/132/conclusion/924/

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

class Codec {
    /*
        n: num of nodes
        time: O(n)
        space: O(n)
    */
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if(root == null) return null;
        
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(root.val);
        for(Node child: root.children) sb.append(serialize(child));
        sb.append("]");
        
        String str = sb.toString();
        
        return str;
    }
	// [1[3[5][6]][2][4]]
    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if(data == null || data.length() == 0) return null;
        
        Stack<Node> stack = new Stack<>();
        
        int num = 0;
        Node root = null;
        for(char c: data.toCharArray()) {
            switch(c) {
                case '[': 
                    stack.push(null);
                    num = 0;
                    break;
                case ']': 
                    Node node = stack.pop();
                    stack.pop();
                    if(stack.size() == 0) root = node;
                    else stack.peek().children.add(node);
                    num = 0;
                    break;
                default:
                    num = num*10 + Integer.valueOf("" + c);
                    if(stack.size() > 0 && stack.peek() != null) stack.pop();
                    stack.push(new Node(num, new ArrayList<>()));    
                    break;
            }
        }
        
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
