# AlbumsListMVVM
This project is over-engineered due to demonstrative purpose. It is implemented in MVVM architecture and divided into 5 layers:
* **_View_** – it is designed to use multiple-activity approach, alternative choice is discussed in potential improvement section.
* **_ViewModel_** – by using Android Lifecycle components, which eases the data persistence with Activity lifecycle and the implementation of reactive programming. e.g. ViewModel, LiveData, Lifecycle.
* **_Domain_** – handles the business logic, usually contains **Repository** (Database, SharedPreference or remote resource represented by proxy) and **Service** (for user actions in terms of business logic, not required in this project).
* **_Proxy_** – parses and prepares the data between the domain layer and the backend. In this project, `AlbumResponseModel` is almost identical to `Album` model. However, in a real life project, which can get much more complicated; for example, an endpoint may be responsible for multiple applications, the response from which may contain many noise attributes, proxy in this case can be a filter and then pass the filtered data stream to domain.
* **_Network_** – fetches raw data from the remote repository.

### Potential Improvements:
* Use one-activity-multiple-fragment with Navigation Component
  - _Pro_ – easy modularisation, easy user journey management
  - _Con_ – Navigation Component library is still in alpha, unstable
* Implement better unhappy path handling structure in order to display more specified error message to improve UX.
* Implement network state change listener to provide user with network state error, such as losing connection.

##### A separate instrumentation test strategy is implemented by creating a separate dependency graph, which is NOT recommended by the project creator.
- The main drawback is that lack of dynamic controls in testing. For instance, it would be extremely inconvenient to create an unhappy path (proxy returns error) while there's already a happy path (proxy returns expected data) in the dependency graph.
- For demonstration, check branch `separate_dependency_graph_instrumentation_test_strategy`.
