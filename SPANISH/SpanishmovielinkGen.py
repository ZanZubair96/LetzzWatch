import requests
from bs4 import BeautifulSoup

def get_movie_links(movie_name):
    # Search for movie links on this website
    url = f"https://yts.autos/browse-movies/{movie_name}/all/all/0/latest/0/sp"
    
    # Send a GET request to the website
    response = requests.get(url)
    
    # Parse the HTML content using BeautifulSoup
    soup = BeautifulSoup(response.content, "html.parser")
    
    # Find all the movie titles and their corresponding links
    movies = soup.find_all("div", {"class": "browse-movie-wrap"})
    
    # Print the links for each movie found
    for movie in movies:
        title = movie.find("a", {"class": "browse-movie-title"}).text
        link = movie.find("a", {"class": "browse-movie-link"})["href"]
        print(f"{title}: {link}")

# Ask user for a movie name and get its links
movie_name = input("Enter a movie name: ")
get_movie_links(movie_name)