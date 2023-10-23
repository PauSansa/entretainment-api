<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a name="readme-top"></a>
<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Don't forget to give the project a star!
*** Thanks again! Now go create something AMAZING! :D
-->



<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=PauSansa_entretainment-api&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=PauSansa_entretainment-api)


<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/PauSansa/entretainment-api">
    <img src="images/logo.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">ENTRETAINMENT API</h3>

  <p align="center">
    
This is my "portfolio" API, where I will implement some of my backend skills that I've been learning throughout my career as a backend developer.
    <br />
    <a href="https://github.com/PauSansa/entretainment-api"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/PauSansa/entretainment-api">View Demo</a>
    ·
    <a href="https://github.com/PauSansa/entretainment-api/issues">Report Bug</a>
    ·
    <a href="https://github.com/PauSansa/entretainment-api/issues">Request Feature</a>
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

[![Product Name Screen Shot][product-screenshot]](https://example.com)

The Entertainment API offers a versatile range of dummy event data that can be seamlessly integrated into a wide array of projects. Whether you are working on an application that requires event information, or you simply want to explore and practice working with JSON data, this API is a valuable resource. 

The event structure is as follows:


```json
{
  "id": "Event ID",
  "name": "Event Name",
  "description": "Event Description",
  "date": "Event Date",
  "category": "Event Category"
}
```



<p align="right">(<a href="#readme-top">back to top</a>)</p>



### Built With

* [![Jav][Java]][Java-url]
* [![Mongo][MongoDB]][MongoDB-url]
* [![SPRING][Spring]][Spring-url]
* [![Jwt][JWT]][JWT-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.

### Prerequisites

In order to run this project locally you must have Java 17 and gradle installed on your computer

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/PauSansa/entretainment-api.git
   ```
2. Set the database connection in the application.yml
    ```yml
        mongodb:
          uri: your mongodb uri
          username: username
          password: password
    ```
2. Run the project using Gradle:
    - On Linux, MacOS or Windows(using powershell)
      ```sh
      ./gradlew bootRun
      ```
    - On Windows (using CMD)
      ```cmd
        gradlew.bat bootRun 
      ```
<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->
## Usage

WIP

_For more examples, please refer to the [Documentation](https://example.com)_

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ROADMAP -->
## Roadmap

This API includes features like:
- ✅ Pagination
- ✅ All CRUD Operations
- ✅ Reactive Connection to MongoDB
- ✅ Auth with JWT
- 🛠️ Filter events by category
- 🛠️ Create users and check to see if they - already exist
- 💭 Deployment using docker
- 💭 AI generated responses


See the [open issues](https://github.com/PauSansa/entretainment-api/issues) for a full list of proposed features (and known issues).

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

Pau Sansa - pausansa59@gmail.com

Project Link: [https://github.com/PauSansa/entretainment-api](https://github.com/PauSansa/entretainment-api)

<p align="right">(<a href="#readme-top">back to top</a>)</p>







<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/PauSansa/entretainment-api.svg?style=for-the-badge
[contributors-url]: https://github.com/PauSansa/entretainment-api/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/PauSansa/entretainment-api.svg?style=for-the-badge
[forks-url]: https://github.com/PauSansa/entretainment-api/network/members
[stars-shield]: https://img.shields.io/github/stars/PauSansa/entretainment-api.svg?style=for-the-badge
[stars-url]: https://github.com/PauSansa/entretainment-api/stargazers
[issues-shield]: https://img.shields.io/github/issues/PauSansa/entretainment-api.svg?style=for-the-badge
[issues-url]: https://github.com/PauSansa/entretainment-api/issues
[license-shield]: https://img.shields.io/github/license/PauSansa/entretainment-api.svg?style=for-the-badge
[license-url]: https://github.com/PauSansa/entretainment-api/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/pausansa
[product-screenshot]: images/screenshot.png
[Java]: https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white
[Java-url]: https://www.oracle.com/java/
[Spring]: https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white
[Spring-url]: https://spring.io/
[JWT]: https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=json-web-tokens&logoColor=white
[JWT-url]: https://jwt.io/
[MongoDB]: https://img.shields.io/badge/MongoDB-47A248?style=for-the-badge&logo=mongodb&logoColor=white
[MongoDB-url]: https://www.mongodb.com/