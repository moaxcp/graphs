{pkgs} :

pkgs.mkShell {
  buildInputs = [ pkgs.graphviz pkgs.adoptopenjdk-bin ];
}