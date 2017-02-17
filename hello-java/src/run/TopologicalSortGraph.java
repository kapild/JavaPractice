package run;

import graph.GraphNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TopologicalSortGraph {

    public static void main(String argc[]) {

        GraphNode root = new GraphNode(1, new ArrayList<GraphNode>());

        GraphNode two = new GraphNode(2, new ArrayList<GraphNode>());
        GraphNode three = new GraphNode(3, new ArrayList<GraphNode>());
        GraphNode four = new GraphNode(4, new ArrayList<GraphNode>());

        GraphNode five = new GraphNode(5, new ArrayList<GraphNode>());

        root.addChild(two);
        root.addChild(three);

        two.addChild(four);
        two.addChild(three);

        three.addChild(four);
        three.addChild(five);

        four.addChild(five);
        List<GraphNode> list = new ArrayList<GraphNode>();

        list.add(root);
        list.add(two);
        list.add(three);
        list.add(four);
        list.add(five);

        Stack<GraphNode> topSOrt = GraphNode.topologicalSort(list, 0);

        while (!topSOrt.isEmpty()) {
            GraphNode node = topSOrt.pop();
            System.out.println(node.val);
        }
    }
}
