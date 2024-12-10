const endpoint = 'http://localhost:8080/api';
const images = [];
let modal;
let modalTitle;
let modalImage;

document.addEventListener('DOMContentLoaded', () => {
  modal = document.getElementById('modal');
  modalTitle = document.getElementById('modal-title');
  modalImage = document.getElementById('modal-image');
});

// Fetches the all the images from the endpoint
async function fetchImages() {
  fetch(endpoint + '/images')
    .then(response => response.json())
    .then(data => {
      images.push(data);
      displayBytes(images[0]);
    }
  );
}

fetchImages();

// Displays the specified images
function displayBytes(images) {
  let galleryElement = document.getElementById('gallery');
  galleryElement.innerHTML = '';

  if (images.length === 0) {
    const errorBox = document.createElement('div');
    errorBox.classList.add('error-box');

    const errorTitle = document.createElement('p');
    errorTitle.classList.add('error-title');
    errorTitle.innerHTML = "No images matching sort parameters."

    errorBox.appendChild(errorTitle);
    galleryElement.appendChild(errorBox);
  }

  for (let i = 0; i < images.length; i++) {
    const image = images[i];

    // Create image-container div
    const imageContainer = document.createElement('div');
    imageContainer.classList.add('image-container');

    // Create image-description box
    const imageDescriptionBox = document.createElement('div');
    imageDescriptionBox.classList.add('image-description-box');

    const imageDescription = document.createElement('h3');
    imageDescription.classList.add('image-description');
    imageDescription.innerHTML = image.location;

    const imageDate = document.createElement('p');
    imageDate.classList.add('image-date');
    imageDate.innerHTML = image.date;

    imageDescriptionBox.appendChild(imageDescription);
    imageDescriptionBox.appendChild(imageDate);
    imageContainer.appendChild(imageDescriptionBox);

    // Create image box
    const imageBox = document.createElement('div');
    imageBox.classList.add('image-box');

    const img = document.createElement('img');
    img.classList.add('image');
    img.id = image.id;
    img.src = endpoint + '/bytesById?id=' + image.id;
    img.alt = image.location;

    imageBox.appendChild(img);
    imageContainer.appendChild(imageBox);
    
    galleryElement.appendChild(imageContainer);

    // Add click listener to each image
    document.getElementById(image.id).addEventListener('click', () => {
      modal.classList.add("show");
      modalTitle.innerHTML = image.location;
      modalImage.id = img.id;
      modalImage.src = img.src;
      modalImage.alt = img.alt;
    });
  }
}

document.getElementById('download-btn').addEventListener('click', () => {
  const url = endpoint + '/bytesById?id=' + modalImage.id;
  const link = document.createElement("a");
  link.href = url;
  link.download = modalTitle.innerHTML;
  document.body.appendChild(link);
  link.click();
  document.body.removeChild(link);
}); 

document.getElementById('close-btn').addEventListener('click', () => {
  modal.classList.remove("show");
});

// Handles the sort by date form submit event
document.getElementById('date-form').addEventListener('submit', async function(event) {
  event.preventDefault();
  const year = document.getElementById('year').value.padStart(4, '0');
  const month = document.getElementById('month').value.padStart(2, '0');
  const day = document.getElementById('day').value.padStart(2, '0');
  const query = `${year}-${month}-${day}`;
  queryImages('/imagesByDate?date=', query);
});

// Handles the sort by location form submit event
document.getElementById('location-form').addEventListener('submit', async function(event) {
  event.preventDefault();
  const query = document.getElementById('location').value;
  queryImages('/imagesByLocation?location=', query);
})

// Fetches the response from the specified URL with the specified query
async function queryImages(url, query) {
  try {
    const response = await fetch(endpoint + url + query);
    const data = await response.json();
    displayBytes(data);
  } catch (error) {
    console.error('Error fetching the image:', error);
  }
}

// Handles the reset form button click event
document.getElementById('form-reset').addEventListener('click', function(event) {
  document.getElementById('year').value = '';
  document.getElementById('month').value = '';
  document.getElementById('day').value = '';
  document.getElementById('location').value = '';
});
