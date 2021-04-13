A simple contrived Secure Visitor Design Pattern example.
Note there are multiple ways to consider a Secure Visitor, for example:
- According to Dougherty et al. 2009, a Secure Visitor operates on hiercarchical (tree) data where each node has different access restrictions. Some sample C++ code is given in that paper
- Secure Visitor does not really have to operate only on trees, or even on a data structure at all. But the most frequent use case is operating on a data structure where:
1. Some of the nodes should not be accessible by the Visitor (the case in the paper)
2. Some of the data in each node (a projection) may be hidden from the Visitor's view.
3. Combine the two. In this case you would be slicing 2 ways - object-level granularity and within-object granularity

This example implements #2.

Compile: javac -d classes src/ser335/SecureVisitorExample.java
Run: java -cp classes ser335.SecureVisitorExample <number of nodes in the list>
