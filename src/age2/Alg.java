package age2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;







// IMPORT OUR TREE PACKAGE
import treeStr.*;

public class Alg {


	// trees' maximum depth
	static int MAX_DEPTH = 20;
	// population's size
	static int POPULATION_SIZE = 10;

	static String[] binOperators = {"+", "-", "/", "*", "**"};
	static String[] unOperators = {"sqrt"};
	static String[] terminals = {"a", "b", "c", "constant"};

	public static void main(String[] args) throws NumberFormatException, Exception {
		// TODO Auto-generated method stub

		//*************************************POPULATION'S GENERATION*********************************

		ArrayList<Tree> population = generatePop();

			/* Checking module - DO NOT MENTION IN THE DOCUMENTATION (won't be here in the final version)
			 *
			population.get(0).getRoot().inorder();
			System.out.println(population.get(0).getRoot().inorder_string());
			 */

		//*************************************EVALUATION OF THE POPULATION*************************************

		/*Double[] eva_puntuacion = new Double[population.size()]; //Save the evaluation of each individual
		for (int j = 0; j < population.size(); j++) {
			eva_puntuacion[j] = Double.parseDouble(getHTML("http://memento.evannai.inf.uc3m.es/age/qesf/?c=" + population.get(j).getRoot().inorder_string()));
		}*/
		//System.out.println(eva_puntuacion[1]);

		//*************************************SELECTION*************************************


		//*************************************CROSSOVER - test version*************************************
		System.out.println("ÁRBOLES QUE SE VAN A CRUZAR");
		System.out.println(population.get(0).getRoot().inorder_string()+","+population.get(0).getRoot().height());
		System.out.println(population.get(1).getRoot().inorder_string()+","+population.get(1).getRoot().height());

		/* "selected" parents (for the final version, this should be an array/list containing the selected
		 * parents from the previous selection process)
		 */
		//Tree[] ind = {population.get(0), population.get(1)};

		// sub-tree's root from each of the parents (these will be crossed)
		Node[] subTRoots = new Node[2];
		// this array will store the index of the parent's descendant to be crossed
		int[] descIndexes = new int[2];

		Random rnd = new Random();

		for(int i=0; i<2; i++){

			Node currentNode = population.get(i).getRoot();
			int toReach = rnd.nextInt(population.get(i).height());

			for(int j=0; j<=toReach; j++){

				/* if currentNode is a terminal, we need to stop the process and establish it
				 * as one of the sub-tree's roots
				 */
				if(currentNode.sons_needed == 0){
					break;
				}else{ // in other case, we keep going through the tree
					descIndexes[i] = rnd.nextInt(currentNode.sons_needed);
					currentNode = currentNode.getSons()[descIndexes[i]];
				}

			} // j for's end

			// we set the last node we were in as one of the sub-tree's roots
			subTRoots[i] = currentNode;

		} // i-for's end

		/* Once we have our sub-tree's root we just have to swap their parents
		 * Exception: when one of the sub-tree's root is the main root of the tree (treated at E#1)
		 */

		// #1 we swap the parent's references
		Node aux = subTRoots[0].parent;
		subTRoots[0].parent = subTRoots[1];
		subTRoots[1].parent = aux;

		// #2 we establish the proper offspring references to these parents
		subTRoots[0].parent.setOneSon(subTRoots[0], descIndexes[1]);
		subTRoots[1].parent.setOneSon(subTRoots[1], descIndexes[0]);

		System.out.println("\nRESULTADO");
		System.out.println(population.get(0).getRoot().inorder_string()+","+population.get(0).getRoot().height());
		System.out.println(population.get(1).getRoot().inorder_string()+","+population.get(1).getRoot().height());
		//*************************************END OF CROSSOVER - test version*************************************

	} // main

	public static ArrayList<Tree> generatePop (){

		ArrayList<Tree> population = new ArrayList<Tree>();

		/* This ArrayList stores the generated nodes (ONLY when they need offspring)
		 * The offspring generator (REF#1) always checks the first individual of this list
		 * When it has no more needed offspring, the node is deleted from the list and the next
		 * individual in it is checked
		 * The process stops when the list is empty
		 * */
		ArrayList<Node> last_generated = new ArrayList<Node>();
		Node node = null;

		Random rnd = new Random();

		for(int i = 0; i<POPULATION_SIZE; i++){
			double node_type = rnd.nextDouble(); // for the root we are not considering using a terminal node


		/* 6 total operators to be considered (5 binary, 1 unary)
		* 1/6 = 0.1666...
		* 0.16 * 5 (bin) = 0.83
		*/
			if(node_type <= 0.83){
				node = new BinN(binOperators[rnd.nextInt(binOperators.length)]);
			}
			if(node_type > 0.83){
				node = new UnN(unOperators[rnd.nextInt(unOperators.length)]);
			}

			Tree myTree = new Tree(node); // new tree with node as root

			last_generated.add(myTree.getRoot());

			Node[] sons = new Node[2];

		/* 10 total operators to be considered (5 binary, 1 unary, 4 terminal)
		* 1/10 = 0.1
		* 0.1 * 5 (bin) = 0.5
		* 0.1 * 4 (un) = 0.4
		*/
			double prob1 = 0.4;
			double prob2 = 0.5;

		/* (REF#1)
		 * a node is included in this list ONLY if it needs offspring to be generated
		 */
			while(last_generated.isEmpty() == false){
				boolean terminales=false;
				int ctr = 0;

				int sons_needed = last_generated.get(0).sons_needed;
				// while the node still needs offspring to be generated...
				while(/*last_generated.get(0).*/sons_needed != 0){

					node_type = rnd.nextDouble();
					//Nodo binario si es por debajo de 0.4
					if(node_type <= prob1){
						node = new BinN(binOperators[rnd.nextInt(binOperators.length)]);
					}
					//Nodo unario si esta entre 0.4 y 0.5
					else if(node_type > prob1 && node_type <= prob2){
						node = new UnN(unOperators[rnd.nextInt(unOperators.length)]);
					}
					//Constante si es por encima de 0.6
					else if(node_type > prob2 || terminales){
						String content = terminals[rnd.nextInt(terminals.length)];
						if(content.equals("constant")){
							content = String.valueOf(rnd.nextInt(1000));
						}
						node = new TerminalN(content);
					}

					node.setParent(last_generated.get(0)); // this new node has the first node of the list as parent

					sons[ctr] = node;
					ctr = ctr+1;
				/*last_generated.get(0).*/sons_needed = /*last_generated.get(0).*/sons_needed-1;

				/* if the node generated is terminal (no needed offspring) it's not added to the list
				* in opposite case, it's added
				*/
					if(node.sons_needed != 0){
						last_generated.add(node);
					}

				}
				// once the needed offspring for a node is generated, it's set and the node is removed from the list
				last_generated.get(0).setSons(sons);
				last_generated.remove(0);
				ctr = 0;

				// if maximum depth is going to be reached, this forces the algorithm to only generate terminal nodes
				if(myTree.height() == MAX_DEPTH - 1){
					prob1 = 0;
					prob2 = 0;
					terminales=true;
				}
			}

			population.add(myTree);
		}

		return population;
	}

	public static String getHTML(String urlToRead) throws Exception {
		StringBuilder result = new StringBuilder();
		URL url = new URL(urlToRead);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		rd.close();
		return result.toString();
	}

}
