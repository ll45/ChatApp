HW08 
Patrick Girardet (pdg2)
Lusia Lu (ll45)

Design/implementation: 
We implemented our ChatApp essentially imitating the detailed design model that the API dictated. 
We have a top-level "main" MVC architecture (consisting of files MainModel/View/Controller and associated 
interfaces and adapters) which sets up the RMI registry and handles connecting and communicating with other 
IUsers through IUserMsg instances. The visitor for handling IUserMsg is defined in MainModel.java.