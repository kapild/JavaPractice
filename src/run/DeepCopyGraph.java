package run;

import graph.GraphNode;

import java.util.ArrayList;
import java.util.HashMap;

public class DeepCopyGraph {

    public static void main(String args[]) {

        GraphNode root = new GraphNode(1, new ArrayList<GraphNode>());

        GraphNode two = new GraphNode(2, new ArrayList<GraphNode>());
        GraphNode three = new GraphNode(3, new ArrayList<GraphNode>());
        GraphNode four = new GraphNode(4, new ArrayList<GraphNode>());

        GraphNode five = new GraphNode(5, new ArrayList<GraphNode>());

        root.addChild(two);
        root.addChild(three);

        two.addChild(four);
        three.addChild(four);

        four.addChild(five);

        GraphNode deepCopyGraphNode = GraphNode.deepCopy(root,
                new HashMap<Integer, GraphNode>());

        GraphNode.breadthPrint(deepCopyGraphNode);

    }
}
