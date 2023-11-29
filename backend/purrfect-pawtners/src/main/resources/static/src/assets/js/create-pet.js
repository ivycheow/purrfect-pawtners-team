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
        } else {
          pawtnerBreedDropdown.disabled = true;
        }

        // Clear and populate the breed dropdown based on the selected option
        populateBreedDropdown(selectedOption);
      });
    }

    // Function to populate the breed dropdown based on the selected pawtnerType
    function populateBreedDropdown(selectedOption) {
      // Array of breeds for Cats and Dogs (you can customize this based on your data)
      var catBreeds = ["Bengal",
      "British Shorthair",
      "Maine Coon",
      "Munchkin",
      "Persian",
      "Ragdoll",
      "Russian Blue",
      "Siamese",
      "Siberian",
      "Singapura"];
      var dogBreeds = ["Affenpinscher",
      "Australian Silky Terrier",
      "Australian Terrier",
      "Beagle",
      "Bichon Frise",
      "Bohemian Terrier",
      "Bolognese",
      "Brussels Griffon (Griffon Bruxaellois)",
      "Bichon Havanese",
      "Border Terrier",
      "Cairn Terrier",
      "Cavalier King Charles Spaniel",
      "Chihuahua",
      "Chinese Crested Dog",
      "Chinese Imperial Chin",
      "Chinese Temple Dog (Classic and Miniature)",
      "Coton de tulear",
      "Czech Terrier",
      "Dachshund (Light and Miniature)",
      "Dandie Dinmont Terrier",
      "English Toy Spaniel",
      "Griffon Belge",
      "German Hunting Terrier",
      "German Shepherd",
      "Golden Retriever",
      "Griffon Brabancon",
      "Hairless Dog",
      "Italian Greyhound",
      "Jack Russell Terrier",
      "Japanese Spaniel (Chin)",
      "Japanese Spitz",
      "Lhasa Apso",
      "Little Lion Dog",
      "Lakeland Terrier",
      "Maltese",
      "Manchester Terrier",
      "Miniature Pinscher",
      "Miniature Schnauzer",
      "Norfolk Terrier",
      "Norwich Terrier",
      "Papillon",
      "Pekinese",
      "Pomeranian",
      "Poodle",
      "Pug",
      "Poodle (Miniature)",
      "Schipperkee",
      "Scottish Terrier",
      "Sealyham Terrier",
      "Shetland Sheep Dog",
      "Shih Tzu",
      "Silky Terrier",
      "Small Continental Spaniel",
      "Small English Terrier",
      "Small Spitz",
      "Smooth Fox Terrier",
      "Toy Fox Terrier",
      "Toy Terrier",
      "Tibetan Spaniel",
      "Volpino Italiano",
      "West Highland Terrier",
      "Wire-Haired Fox Terrier",
      "Welsh Terrier",
      "Yorkshire Terrier",];

      // Clear existing options
      pawtnerBreedDropdown.innerHTML = '<option selected>Choose...</option>';

      // Populate options based on the selected pawtnerType
      var breeds = selectedOption === 'Cats' ? catBreeds : selectedOption === 'Dogs' ? dogBreeds : [];
      breeds.forEach(function (breed) {
        var option = document.createElement('option');
        option.value = breed;
        option.text = breed;
        pawtnerBreedDropdown.appendChild(option);
      });
    };

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
      // Only allow letters and whitespace
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
