// Create a backup of the original items
const originalItems = document.querySelectorAll('.filterDiv');

function filterSelection() {
    const typeFilter = document.getElementById('pawtnerTypeFilter').value;
    const genderFilter = document.getElementById('pawtnerGenderFilter').value;
    const hdbApprovedFilter = document.getElementById('pawtnerHDBApprovedFilter').value;

    const container = document.querySelector('.album-container');
    container.innerHTML = ''; // Clear the container before adding filtered items

    let foundResults = false; // Flag to track if any results were found

    originalItems.forEach(originalItem => {
        if (matchesFilter(originalItem, typeFilter, genderFilter, hdbApprovedFilter)) {
            // Append the original item to the container
            container.appendChild(originalItem);
            foundResults = true; // Set the flag to true since a result was found
        }
    });

    // Display a message if no results were found
    if (!foundResults) {
        const noResultsMessage = document.createElement('p');
        noResultsMessage.textContent = 'No results found based on the filter selection.';
        container.appendChild(noResultsMessage);
    }
}

// Restoring the original items when filters are cleared
function restoreOriginalItems() {
    const container = document.querySelector('.album-container');
    container.innerHTML = ''; // Clear the container before adding original items

    originalItems.forEach(originalItem => {
        // Append the original item to the container
        container.appendChild(originalItem);
    });
}

  function matchesFilter(item, typeFilter, genderFilter, hdbApprovedFilter) {
    const itemType = item.dataset.type;
    const itemGender = item.dataset.gender;
    const itemHDBApproved = item.dataset.hdbapproved;
  
    const typeMatch = typeFilter === 'All' || itemType === typeFilter;
    const genderMatch = genderFilter === 'All' || itemGender === genderFilter;
    const hdbApprovedMatch = hdbApprovedFilter === 'All' || itemHDBApproved === hdbApprovedFilter;
  
    return typeMatch && genderMatch && hdbApprovedMatch;
  }  

// Add event listeners to the select elements
document.getElementById('pawtnerTypeFilter').addEventListener('change', filterSelection);
document.getElementById('pawtnerGenderFilter').addEventListener('change', filterSelection);
document.getElementById('pawtnerHDBApprovedFilter').addEventListener('change', filterSelection);

// Function to clear filter selections
function clearFilters() {
    document.getElementById('pawtnerTypeFilter').value = 'All';
    document.getElementById('pawtnerGenderFilter').value = 'All';
    document.getElementById('pawtnerHDBApprovedFilter').value = 'All';

    // Call restoreOriginalItems to display all original items
    restoreOriginalItems();
}

// Add event listener to the "Clear Filter" button
document.querySelector('.clear-filter-button').addEventListener('click', clearFilters);
