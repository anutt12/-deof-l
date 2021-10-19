#### paypal-java-project-2

# ôdeofīl 
_**A place for people to organize and share their personal physical music collection.**_

## ERD Diagram

![ERD Diagram Generated with LucidApp](/Users/matthompson/Desktop/java-project-2/paypal-java-project-2/project-images/Lucid_ERD.jpeg)

![ERD Diagram Generated with IntelliJ](/Users/matthompson/Desktop/java-project-2/paypal-java-project-2/project-images/paypal-java-project-2.uml)

### User Stories 
**(Bronze)**  
• As a user, I should be able to create an account  
• As a user, I should be able to log into my account  
• As a user, I should be able to add music to my library  
• As a user, I should be able to search my library  
• As a user, I should be able to view my library  
• As a user, I should be able to remove music from my library  

**(Silver)**  
• As a user, I should be able to search the music database for a specific release  
• As a user, I should be able to view my friends’ libraries  
• As a user, I should be able to comment on my friends’ libraries  
• As a user, I should be able to keep a wish list of music copies I want to collect  

**(Gold)**  
• As a user, I should be able to trade my music copies with friends  
• As a user, I should be able to save a pending release for when it comes out  
• As a user, I should be able to see where local music shops are (Google Map API)  

**(Platinum/Diamond)**  
• As a user I should be able to share my purchase on social media  
• As a user I should be able to share my wish list on social media  
• As a user, I should be able to talk with friends in real time  

## HTTP Path Mappings
| **HTTP REQUEST** | **PATH** | **DESCRIPTION** |
| ---------------- | -------- | --------------- |
| GET | /api/libraries/ | Retrieve all libraries |  
| POST | /api/libraries/ | Create a library |  
| GET | /api/libraries/{libraryId} | Retrieve a single library |  
| PUT | /api/libraries/{libraryId} | Update a library |
| DELETE | /api/libraries/{libraryId} | Delete a library |
| GET | /api/libraries/{libraryId}/albums | Retrieve all albums in a library |
| POST | /api/libraries/{libraryId}/albums | Add an album to the library |
| GET | /api/libraries/{libraryId}/albums/{albumId} | Retrieve a single album |
| PUT | /api/libraries/{libraryId}/albums/{albumId} | Update an album |
| DELETE | /api/libraries/{libraryId}/albums/{albumId} | Delete an album |
| GET | /api/libraries/{libraryId}/artists | Retrieve all artists in a library |
| POST | /api/libraries/{libraryId}/artists | Add an artist to the library |
| GET | /api/libraries/{libraryId}/artists/{artistId} | Retrieve a single artist |
| PUT | /api/libraries/{libraryId}/artists/{artistId} | Update an artist |
| DELETE | /api/libraries/{libraryId}/artists/{artistId} | Delete an artist |
| POST | /auth/users/login | Login to the app |
| POST | /auth/users/register | Create a user profile |

## Technology Used
• Spring Boot, Java, Maven in IntelliJ  
• LucidApp  
• Postman  
• PostgreSQL  
• Microsoft Sharepoint (Word, Planner)  

## Our Approach:
We wanted to find a real world need to address with this project. Andy is an avid vinyl collector but found he didn't have a way to know if he had a physical copy at home while browsing in a store. We spent our first day planning on how we would proceed with the project. We found [Discogs API](https://www.discogs.com/developers) which would help us access their existing database of physical releases of music.  
 \
We utilized Microsoft Sharepoint to help us organize our plan for this project. Word allowed us to work on our User Stories' together at the same time. We also used Planner to create a digital version of a Kanban Board/To Do List to make sure we stayed on track. We created separate to do lists for various packages in the project and included an undefined package for things that did not exactly fit in the others. We enjoyed using this tool and found it could be used in future/longer term projects. It allows you to assign due date to a task and a task to an individual.

## :rose: Roses and Thorns :cactus:

