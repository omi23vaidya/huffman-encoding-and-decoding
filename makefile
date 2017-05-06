# Initialize
JCC = javac
JFLAGS = -g
RM = rm

# Build
default: evaluation.class decoder.class encoder.class

evaluation.class: evaluation.java
	$(JCC) $(JFLAGS) evaluation.java

decoder.class: decoder.java
	$(JCC) $(JFLAGS) decoder.java

encoder.class: encoder.java
	$(JCC) $(JFLAGS) encoder.java