var data = [];

function getPetIdByName(petName) {
  const apiUrl = `/pets/?name=${petName}`;

  return fetch(apiUrl)
    .then((response) => {
      if (!response.ok) {
        throw new Error("Failed to fetch petId");
      }
      return response.json();
    })
    .then((data) => {
      return data.id;
    })
    .catch((error) => {
      console.error("Error fetching petId: ", error);
    });
}

// creates a class that controls the listing of products
class Controller {
  // constructor of a class is typically used for initialisation
  // 'this' is a keyword, properties that belong to this class are declared here
  // start the current id based on the passed in value (or default to zero)
  // and store the passed-in param 'data' to localStorage via storeDataTolocalStorage

  constructor(currentId = 0, data = []) {
    this.products = data !== null && data; // class Controller's property: products
    this.currentId = currentId; // class Controller's property: currentId

    this.storeDataToLocalStorage(data);
  }

  // storeDataToLocalStorage() method belongs to class Controller
  // if localStorage 'productList' doesn't exist
  // ensure there are values in 'data' before storing the data
  // current id is used here to determine the currentId in this instance of the object
  // stores the param's data into the web browser's localStorage
  // the localStorage variable is defined with the name 'productList'

  storeDataToLocalStorage(data) {
    if (!localStorage.getItem("productList")) {
      const sampleItems = [];
      if (data.length > 0) {
        for (let index = 0; index < this.currentId; index++) {
          sampleItems.push({
            id: index + 1, // increment the id
            name: data[index].name,
            ageYear: data[index].ageYear,
            ageMonths: data[index].ageMonths,
            type: data[index].type,
            breed: data[index].breed.name,
            imagePath: data[index].imagePath,
            gender: data[index].gender,
            color: data[index].color,
            licensed: data[index].isLicensed,
            hdbApproved: data[index].isApproved,
            spaying: data[index].isNeutered,
            training: data[index].training,
            temperament: data[index].temperament,
          });
        }
      }
      localStorage.setItem("productList", JSON.stringify(sampleItems));
    }
  }

  // loadDataFromLocalStorage() method belongs to class Controller
  // loads data called 'productList' from localStorage - see helpers.js
  // after 'productList' is loaded from localStorage to local constant 'products'
  // pass the values from 'products' to method displayCart() to display the contents

  loadDataFromLocalStorage() {
    const storageItems = localStorage.getItem("productList");
    if (storageItems) {
      const products = JSON.parse(storageItems);
      this.displayCart(products);
    }
  }

  // displayCart() method belongs to class Controller
  // it receives an array of Objects
  // an instance unorderedlist is instantiated to reference classname "all-cards-container"

  // (A) in the event there are NO products
  // reset the gridTemplateColumns of class "all-cards-container"
  // and displays feedback that there are no products at the moment
  // and exit the function displayCart() prematurely - via return;

  // (B) in the event there are products
  // a for loop is used to iteratively populate class name "all-cards-container"
  // with a list of items representing each product received from param 'data'

  displayCart(data) {
    const unorderedList = document.querySelector(".album-container");

    // (A)
    if (!data.length) {
      unorderedList.style.gridTemplateColumns = "none";
      let listItem = document.createElement("li");
      listItem.innerHTML = `<span class=\"no-products\">No listing available.</span>`;
      unorderedList.appendChild(listItem);
      return;
    }

    // (B)
    for (let index = 0; index < data.length; index++) {
      const petName = data[index].name;
      const modalId = `deletePetModal_${index}`; // Unique modal id

      let ageInfo =
        data[index].ageYear > 0 ? `${data[index].ageYear} years` : "";
      ageInfo +=
        data[index].ageMonths > 0 ? ` ${data[index].ageMonths} months` : "";

      let listItem = document.createElement("div");
      listItem.className = `col pet-item`;
      listItem.innerHTML = `
            <div class="card shadow-sm adoption-card filterDiv ${data[index].type}" 
              data-type="${data[index].type}" 
              data-gender="${data[index].gender}" 
              data-hdbapproved="${data[index].isApproved}">
                    <img class="bd-placeholder-img card-img-top album-card-img" width="100%" height="225" src="${data[index].imagePath}"/>
                    <div class="card-body album-card-body">
                        <h2>${data[index].name}</h2>
                        <p>${ageInfo} old // ${data[index].gender}</p>
                        <div class="d-flex justify-content-between align-items-center">
                            <button type="button" class="btn btn-primary album-card-button">
                              Update
                            </button>

                            <button type="button" class="btn btn-danger" id="delete-button" onClick="handleDeleteButtonClick('${petName}')">
                              Delete
                            </button>

                            <div class="modal fade" id="deletePetModal" tabindex="-1" role="dialog" aria-labelledby="deletePetModalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                              <div class="modal-content">
                                <div class="modal-header">
                                  <h5 class="modal-title" id="deletePetModalLabel">Confirm Delete</h5>
                                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                  </button>
                                </div>
                                <div class="modal-body">
                                  Are you sure you want to delete this pet?
                                </div>
                                <div class="modal-footer">
                                  <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                  <button type="button" class="btn btn-danger" id="confirmDeleteButton">Delete</button>
                                </div>
                              </div>
                            </div>
                          </div>


                          </div>
                        </div>
              </div>
            `;

      // const deleteButton = listItem.querySelector(".btn-danger");
      //   deleteButton.addEventListener("click", () => {
      //       this.handleDeleteButtonClick(petName);
      //   });

      unorderedList.appendChild(listItem);
    }
  }

  // addProduct() method belongs to class Controller
  // when called, addProduct instnatiates a constant that stores the passed-in params
  // and pushes each new product into products property (refer to constructor)
  // and adds each product to localStorage via its method setItem()

  // (A) if 'productList' exists in localStorage, append to it and exit (return)
  // (B) otherwise, use the products property of Controller

  addProduct(
    name,
    ageYear,
    ageMonths,
    type,
    breed,
    imagePath,
    gender,
    color,
    licensed,
    hdbApproved,
    spaying,
    training,
    temperament
  ) {
    const storageItems = localStorage.getItem("productList");

    // (A)
    if (storageItems) {
      const products = JSON.parse(storageItems);
      console.log(`Testing products length ${products.length}`);
      const product = {
        id: products.length + 1, // increment the id for each newly added product
        name: name,
        ageYear: ageYear,
        ageMonths: ageMonths,
        type: type,
        breed: breed.name,
        imagePath: imagePath,
        gender: gender,
        color: color,
        isLicensed: isLicensed,
        isApproved: isApproved,
        isNeutered: isNeutered,
        training: training,
        temperament: temperament,
      };
      products.push(product);
      localStorage.setItem("productList", JSON.stringify(products));
      return;
    }

    // (B)
    const setId = !storageItems ? 1 : storageItems.length++;

    const product = {
      id: setId, // this.currentId++,
      name: name,
      ageYear: ageYear,
      ageMonths: ageMonths,
      type: type,
      breed: breed.name,
      imagePath: imagePath,
      gender: gender,
      color: color,
      isLicensed: isLicensed,
      isApproved: isApproved,
      isNeutered: isNeutered,
      training: training,
      temperament: temperament,
    };
    this.products.push(product);
    localStorage.setItem("productList", JSON.stringify(this.products));
  }
}

productsController = new Controller(data.length, data);
// productsController.loadDataFromLocalStorage();

async function fetchData() {
  let response = await fetch("/pets/all");
  let data = await response.json();
  // console.log(data);
  productsController.displayCart(data);
}

fetchData();

function handleDeleteButtonClick(petName) {
  getPetIdByName(petName)
      .then(petId => {
          // Dynamically generate the unique modal ID
          const modalId = `deletePetModal_${petId}`;
          
          // Trigger the modal using Bootstrap's modal methods
          const modalElement = document.getElementById(modalId);

          // Check if the modal element is found
          if (modalElement) {
              modalElement.classList.add('show');
              modalElement.style.display = 'block';

              // Add an event listener to the parent modal for delegation
              modalElement.addEventListener("click", function(event) {
                  if (event.target.id === "confirmDeleteButton") {
                      // Handle the delete action here
                      const apiDeleteUrl = `/pets/delete/${petId}`;

                      fetch(apiDeleteUrl, {
                          method: "DELETE"
                      })
                      .then(response => {
                          if(response.ok) {
                              alert("Pet deleted successfully");
                              window.location.reload();
                          } else{
                              alert("Failed to delete pet. Please try again.")
                          }
                      })
                      .catch(error => {
                          console.error("Error deleting pet:", error);
                          alert("An error occurred while deleting the pet. Please try again.")
                      });
                  }
              });
          } else {
              console.error("Modal element not found");
          }
      })
      .catch(error => {
          console.error("Error: ", error);
          alert("An error occurred while fetching the pet. Please try again.");
      });
};

