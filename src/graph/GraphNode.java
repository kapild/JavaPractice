package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class GraphNode {

    public int val;

    List<GraphNode> children;

    public GraphNode(int val, List<GraphNode> children) {
        this.val = val;
        this.children = children;
    }

    public GraphNode() {
        this(0, new ArrayList<GraphNode>());
    }

    public void addChild(GraphNode node) {
        children.add(node);
    }

    public static Stack<GraphNode> topologicalSort(List<GraphNode> listNodes,
            int indexOfRoot) {

        return runDfs(listNodes, indexOfRoot);

    }

    public static Stack<GraphNode> runDfs(List<GraphNode> listNodes,
            int indexOfRoot) {

        Stack<GraphNode> stack = new Stack<GraphNode>();

        Map<Object, Integer> color = new HashMap<Object, Integer>();
        Map<Object, Object> parent = new HashMap<Object, Object>();

        color.put(listNodes.get(indexOfRoot).val, 0);
        parent.put(listNodes.get(indexOfRoot).val, null);
        dfsRecursive(listNodes.get(indexOfRoot), color, parent, stack);

        for (GraphNode node : listNodes) {
            if (!color.containsKey(node.val)) {
                dfsRecursive(node, color, parent, stack);
            }
        }
        return stack;
    }

    public static void dfsRecursive(GraphNode node, Map<Object, Integer> color,
            Map<Object, Object> parent, Stack<GraphNode> stack) {

        // GRAY
        color.put(node.val, 1);

        for (GraphNode child : node.children) {
            if (!color.containsKey(child.val)) {
                parent.put(child.val, node.val);
                dfsRecursive(child, color, parent, stack);
            }
        }

        stack.push(node);
        // BLACK
        color.put(node.val, 2);
    }

    public static GraphNode deepCopy(GraphNode root, Map<Integer, GraphNode> map) {

        if (root == null) {
            return null;
        }

        GraphNode newNode = new GraphNode();
        newNode.val = root.val;

        Iterator<GraphNode> it = root.children.iterator();

        while (it.hasNext()) {
            GraphNode nextNode = it.next();
            if (!map.containsKey(nextNode.val)) {
                GraphNode deepCopyNode = GraphNode.deepCopy(nextNode, map);
                newNode.addChild(deepCopyNode);
                map.put(nextNode.val, deepCopyNode);
            } else {
                newNode.addChild(map.get(nextNode.val));
            }
        }
        return newNode;
    }

    public static void breadthPrint(GraphNode node) {

        Queue<GraphNode> queue = new ArrayDeque<GraphNode>();

        Set<Integer> visited = new HashSet<Integer>();
        queue.add(node);
        visited.add(node.val);
        while (!queue.isEmpty()) {
            GraphNode current = queue.poll();
            System.out.println("Parent:" + current.val);
            for (GraphNode child : current.children) {
                System.out.println("\tChild:" + child.val);
                if (!visited.contains(child.val)) {
                    queue.add(child);
                    visited.add(child.val);

                }
            }
        }
    }
}
