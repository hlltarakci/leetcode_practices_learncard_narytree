// https://leetcode.com/explore/learn/card/n-ary-tree/130/traversal/915/

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
        time: O(n)
        space: O(n) -- worst case -- all children are at same level
    */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> lists = new ArrayList<>();
        
        if(root == null) return lists;
        
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        
        List<Integer> list = new ArrayList<>();
        while(!queue.isEmpty()) {
            Node curr = queue.remove();
            if(curr == null) {
                lists.add(list);
                list = new ArrayList<>();
                if(!queue.isEmpty()) queue.add(null);
            } else {
                list.add(curr.val);
                for(Node child: curr.children) queue.add(child);
            }
        }
        
        return lists;
    }
}
