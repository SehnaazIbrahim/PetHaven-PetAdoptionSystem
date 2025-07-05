import React from 'react';
import './PetCard.css';
import { adoptPet } from '../services/petService'; // Adjust path if needed

const PetCard = ({ pet, refresh = () => {}, showAdoptButton = true }) => {
  if (!pet) return null;

  const handleAdopt = async () => {
    try {
      const user = JSON.parse(localStorage.getItem('user'));
      const email = user?.email;

      if (!email) {
        alert("Please log in to adopt a pet.");
        return;
      }

      await adoptPet(pet.id); // Calls petService with user email
      alert("Adoption request sent successfully!");

      // Optional: reload or refresh parent
      refresh();
    } catch (err) {
      console.error(err);
      alert("Adoption failed. Please try again.");
    }
  };

  return (
    <div className="pet-card">
      <img
        src={pet.imageUrl || 'https://via.placeholder.com/300x200'}
        alt={pet.name || 'Pet'}
        className="pet-image"
      />
      <div className="pet-details">
        <h3>{pet.name || 'Unknown'}</h3>
        <p><strong>Breed:</strong> {pet.breed || 'Unknown'}</p>
        <p><strong>Age:</strong> {pet.age || 'N/A'} years</p>
        <p><strong>Description:</strong> {pet.description || 'No description'}</p>
        <p><strong>Status:</strong> {pet.status || 'Available'}</p>

        {showAdoptButton && pet.status !== "Adopted" && (
          <button className="adopt-btn" onClick={handleAdopt}>
            Adopt
          </button>
        )}

        {pet.status === "Adopted" && (
          <button className="adopt-btn" disabled>Already Adopted</button>
        )}
      </div>
    </div>
  );
};

export default PetCard;
