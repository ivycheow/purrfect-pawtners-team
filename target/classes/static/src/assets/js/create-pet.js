document.addEventListener("DOMContentLoaded", function () {
  // create-a-pet-form breedDropdown
    // Get the pawtnerType dropdown and breed dropdown
    var pawtnerTypeDropdown = document.getElementById('pawtnerType');
    var pawtnerBreedDropdown = document.getElementById('pawtnerBreed');

    // Add event listener to the pawtnerType dropdown
    if (pawtnerTypeDropdown && pawtnerBreedDropdown) {
      // Add event listener to the pawtnerType dropdown
      pawtnerTypeDropdown.addEventListener('change', function () {
        // Get the selected option
        var selectedOption = pawtnerTypeDropdown.options[pawtnerTypeDropdown.selectedIndex].value;

        // Enable or disable the breed dropdown based on the selected option
        if (selectedOption === 'Cats' || selectedOption === 'Dogs') {
          pawtnerBreedDropdown.disabled = false;
          fetchBreeds(selectedOption);
        } else {
          pawtnerBreedDropdown.disabled = true;
        }

        // Clear and populate the breed dropdown based on the selected option
        // populateBreedDropdown(selectedOption);
      });
    }

    // Function to populate the breed dropdown based on the selected pawtnerType
    function fetchBreeds(selectedOption) {
      var breedUrl = "/breed/type/" + selectedOption;
      pawtnerBreedDropdown.innerHTML = '<option selected>Loading...</option>';

      fetch(breedUrl).then(response => response.json()).then(data => {
        populateBreedDropdown(data);
      }).catch(error => {
        console.error('Error fetching breeds:', error);
        pawtnerBreedDropdown.innerHTML = '<option selected>Error loading breeds</option>';
      })
    };

    function populateBreedDropdown(breeds){
      breeds.sort((a, b) => a.name.localeCompare(b.name));
      
      pawtnerBreedDropdown.innerHTML = '<option selected>Choose...</option>';
      breeds.forEach(function(breed){
        var option = document.createElement('option');
        option.value = breed.name;
        option.text = breed.name;
        pawtnerBreedDropdown.appendChild(option);
        console.log(option);
      })
    }

  // create-pet AgeYear
  const ageYearDropdown = document.getElementById("pawtnerAgeYear");
    for (let i = 1; i <= 10; i++) {
      const option = document.createElement("option");
      option.textContent = i;
      ageYearDropdown.appendChild(option);
    }

  // create-pet AgeMonths
  const ageMonthsDropdown = document.getElementById("pawtnerAgeMonths");
  for (let i = 1; i <= 11; i++) {
    const option = document.createElement("option");
    option.textContent = i;
    ageMonthsDropdown.appendChild(option);
  }

  // create-pet Colour Name Temperament Training text validation
  const fieldsToValidate = [
    "pawtnerName",
    "pawtnerColour",
    "pawtnerTemperament",
    "pawtnerTraining"
  ];

  fieldsToValidate.forEach(function (fieldId) {
    const inputField = document.getElementById(fieldId);
    inputField.addEventListener("input", function () {
      this.value = this.value.replace(/[^A-Za-z\s\'!.-;]/g, "");
    });
  });
});

let imgUrl;

function previewImage() {
  var fileInput = document.getElementById("pawtnerImage");
  var preview = document.getElementById("imagePreview");

  if (fileInput.files && fileInput.files[0]) {
    var file = fileInput.files[0];

    // Validate file type and size
    var validExtensions = ["jpg", "jpeg", "png"];
    var fileExtension = file.name.split('.').pop().toLowerCase();
    var isValidExtension = validExtensions.indexOf(fileExtension) !== -1;
    var isValidSize = file.size <= 5 * 1024 * 1024; // 5MB

    if (isValidExtension && isValidSize) {
      var reader = new FileReader();

      reader.onloadend = function () {
        imgUrl = reader.result;
        preview.src = reader.result;
        preview.style.display = "block";
      };

      reader.readAsDataURL(file);
    } else {
      alert("Invalid file. Please upload a valid image file (JPG, JPEG, or PNG) with a size less than 5MB.");
      // Clear the file input
      fileInput.value = "";
      preview.src = "images/create-pet-image-placeholder.png";
      preview.style.display = "block"; 
    }
  } else {
    preview.src = "";
  }
}

// Initialize a new ItemsController with currentId set to 0
let productsController = new Controller(data.length, data);

// Select the New Item Form
const newProductForm = document.querySelector('#createPetForm');

// Add an 'onsubmit' event listener
newProductForm.addEventListener('submit', (event) => {
    // Prevent default action
    event.preventDefault();

    window.scrollTo(0, 0);

    // Select the inputs
    const newProductName = document.querySelector('#pawtnerName'); //Name
    const newProductAgeYear = document.querySelector('#pawtnerAgeYear'); //AgeYear
    const newProductAgeMonths = document.querySelector('#pawtnerAgeMonths'); //AgeMonths
    const newProductType = document.querySelector('#pawtnerType'); //Type
    const newProductBreed = document.querySelector('#pawtnerBreed'); //Breed
    const newProductImagePath = document.querySelector('#pawtnerImage'); //Image
    const newProductGender = document.querySelector('input[name="pawtnerGender"]:checked'); //Gender
    const newProductColor = document.querySelector('#pawtnerColour'); //Colour
    const newProductIsLicensed = document.querySelector('input[name="pawtnerAVSLicensed"]:checked'); //Licensed
    const newProductIsApproved = document.querySelector('input[name="pawtnerHDBApproved"]:checked'); //HDB-Approved
    const newProductIsNeutered = document.querySelector('input[name="pawtnerSpayNeuter"]:checked') //Spaying/Neutering
    const newProductTraining = document.querySelector('#pawtnerTraining') //Training
    const newProductTemperament = document.querySelector('#pawtnerTemperament') //Temperament

    // Get the values of the inputs
    const name = newProductName.value;
    const ageYear = newProductAgeYear.value;
    const ageMonths = newProductAgeMonths.value;
    const selectedIndex = newProductBreed.selectedIndex; //To obtain the index of breed option selected in the dropdown list above
    const type = newProductType.value;
    const breed = newProductBreed.options[selectedIndex].text; //To target the index in the options(dropdown list) and return the text
    const imagePath = newProductImagePath.value;
    const gender = newProductGender.value;
    const color = newProductColor.value;
    const isLicensed = newProductIsLicensed.value;
    const isApproved = newProductIsApproved.value;
    const isNeutered = newProductIsNeutered.value;
    const training = newProductTraining.value;
    const temperament = newProductTemperament.value;

    // Add the item to the ItemsController
    productsController.addProduct(name, ageYear, ageMonths, type, breed, imagePath, gender, color, isLicensed, isApproved, isNeutered, training, temperament);

    // Bootstrap 5 inclusions
    // Run BootStrap5's toast to show the activity is complete.
    var toastEl = document.querySelector('.toast');
    var toast = new bootstrap.Toast(toastEl);
    toast.show();

    // Clear the form

    var form = document.getElementById("createPetForm"); // Replace "yourFormId" with the actual ID of your form
    form.reset();
  
    // Clear the image preview
    var preview = document.getElementById("imagePreview");
    preview.src = 'images/create-pet-image-placeholder.png';
});

// Bootstrap 5 inclusions
var productAddedToast = document.querySelector('.toast');
productAddedToast.addEventListener('hidden.bs.toast', function () {
      window.open("adoption.html", "productlistwindow");
})
