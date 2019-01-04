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
* Alternative AndroidTest approach.
In this project, a separate Dependency Graph is created which altered proxy and network layers in order to plugin stubbed data and avoid to hit network in instrumentation testing.
Alternatively, Dagger could be disabled for instrumentation testing and mock ViewModels and Domain layers with Mockito in each individual AndroidTest class.
	- _Pro_ – each test class is decoupled from the whole project, less maintenance overhead
	- _Con_ – lose the opportunity of testing business logic with instrumentation test, much more and duplicated stub work in each test class, time consuming
	- ```See master branch for this approach.```
* Implement better unhappy path handling structure in order to display more specified error message to improve UX.
* Implement network state change listener to provide user with network state error, such as losing connection.
