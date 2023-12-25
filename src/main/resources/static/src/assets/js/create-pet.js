document.addEventListener("DOMContentLoaded", function () {
  // pawtnerType and pawtnerBeed Dropdown
  var pawtnerTypeDropdown = document.getElementById("pawtnerType");
  var pawtnerBreedDropdown = document.getElementById("pawtnerBreed");

  // Add event listener to the pawtnerType dropdown
  if (pawtnerTypeDropdown && pawtnerBreedDropdown) {
    pawtnerTypeDropdown.addEventListener("change", function () {
      // Get the selected option
      var selectedOption =
        pawtnerTypeDropdown.options[pawtnerTypeDropdown.selectedIndex].value;

      // Enable or disable the breed dropdown based on the selected option
      if (selectedOption === "Cats" || selectedOption === "Dogs") {
        pawtnerBreedDropdown.disabled = false;
        fetchBreeds(selectedOption);
      } else {
        pawtnerBreedDropdown.disabled = true;
      }
    });
  }

  // Function to populate the breed dropdown based on the selected pawtnerType
  function fetchBreeds(selectedOption) {
    var breedUrl = "/breed/type/" + selectedOption;
    pawtnerBreedDropdown.innerHTML = "<option selected>Loading...</option>";

    fetch(breedUrl)
      .then((response) => response.json())
      .then((data) => {
        populateBreedDropdown(data);
      })
      .catch((error) => {
        console.error("Error fetching breeds:", error);
        pawtnerBreedDropdown.innerHTML =
          "<option selected>Error loading breeds.</option>";
      });
  }

  function populateBreedDropdown(breeds) {
    breeds.sort((a, b) => a.name.localeCompare(b.name));

    pawtnerBreedDropdown.innerHTML = "<option selected>Choose...</option>";
    breeds.forEach(function (breed) {
      var option = document.createElement("option");
      option.value = breed.id;
      option.text = breed.name;
      pawtnerBreedDropdown.appendChild(option);
      console.log(option);
    });
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
    "pawtnerTraining",
  ];

  fieldsToValidate.forEach(function (fieldId) {
    const inputField = document.getElementById(fieldId);
    inputField.addEventListener("input", function () {
      // Only allow letters and whitespace
      this.value = this.value.replace(/[^A-Za-z\s\'!.-;]/g, "");
    });
  });
});

let imageData;

function previewImage() {
  var fileInput = document.getElementById("pawtnerImage");
  var preview = document.getElementById("imagePreview");

  if (fileInput.files && fileInput.files[0]) {
      var file = fileInput.files[0];

      // Validate file type and size
      var validExtensions = ["jpg", "jpeg", "png"];
      var fileExtension = file.name.split(".").pop().toLowerCase();
      var isValidExtension = validExtensions.includes(fileExtension);
      var isValidSize = file.size <= 5 * 1024 * 1024; // 5MB

      if (isValidExtension && isValidSize) {
          var reader = new FileReader();

          reader.onloadend = function () {
              preview.src = reader.result; // Set the preview image src
              preview.style.display = "block";
          };

          reader.readAsDataURL(file);
      } else {
          alert(
              "Invalid file. Please upload a valid image file (JPG, JPEG, or PNG) with a size less than 5MB."
          );
          fileInput.value = "";
          preview.src = "images/create-pet-image-placeholder.png"; // Reset to placeholder if invalid
          preview.style.display = "block";
      }
  } else {
      preview.src = "images/create-pet-image-placeholder.png"; // Reset to placeholder if no file
  }
}

var fileInput = document.getElementById("pawtnerImage");
if (fileInput) {
    fileInput.addEventListener('change', previewImage);
}

let productsController = new Controller(0, []);

// Select the New Item Form
const newProductForm = document.querySelector("#createPetForm");

function validateFormData(formData) {
  for (const key in formData) {
    if (formData.hasOwnProperty(key)) {
      const value = formData[key];
      // Check for undefined, null, NaN (specifically for numbers), and empty strings
      if (
        value === undefined ||
        value === null ||
        (typeof value === "number" && isNaN(value)) ||
        (typeof value === "string" && value.trim() === "")
      ) {
        console.error(`Validation error: ${key} is invalid. Value:`, value);
        return false;
      }
    }
  }
  return true;
}

// Add an 'onsubmit' event listener
newProductForm.addEventListener("submit", async (event) => {
  console.log("Form submission event triggered.");

  // Prevent default action
  event.preventDefault();
  window.scrollTo(0, 0);

  // Create FormData from the form
  const formData = new FormData(event.target);

  // Append the image file to formData
  const fileInput = document.querySelector("#pawtnerImage").files[0];
  formData.append("pawtnerImage", fileInput);

  // Log formData contents
  for (let [key, value] of formData.entries()) {
    console.log(`${key} (Type: ${typeof value}): ${value}`);
  }

  console.log("Sending POST request to server...");

  try {
    const response = await fetch("http://localhost:8080/pets/", {
      method: "POST",
      body: formData,
    });

    if (response.ok) {
      console.log("Pet added successfully");
      // Reset the form and preview after successful submission
      event.target.reset();
      document.getElementById("imagePreview").src =
        "images/create-pet-image-placeholder.png";
    } else {
      console.error("Failed to add pet.");
    }
  } catch (error) {
    console.error("Error during fetch:", error);
  }

  // Display the toast message
  var toastEl = document.querySelector(".toast");
  var toast = new bootstrap.Toast(toastEl);
  toast.show();

  // // Clear the form
  // var form = document.getElementById("createPetForm"); // Replace "yourFormId" with the actual ID of your form
  // form.reset();
  // console.log("Form has been reset.");

  // Clear the image preview
  var preview = document.getElementById("imagePreview");
  preview.src = "images/create-pet-image-placeholder.png";
});

// Bootstrap 5 inclusions
var productAddedToast = document.querySelector(".toast");
productAddedToast.addEventListener("hidden.bs.toast", function () {
  window.open("adoption.html", "productlistwindow");
});

// Function to get the value of the selected radio button
function getSelectedRadioValue(radioButtons) {
  for (const radioButton of radioButtons) {
    if (radioButton.checked) {
      return radioButton.value;
    }
  }
  // Return a default value or null if none are selected
  return null;
}
