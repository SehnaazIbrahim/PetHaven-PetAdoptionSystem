import React from 'react';
import './PetCard.css';

const PetCard = ({ pet, onAdopt = () => {}, showAdoptButton = true }) => {
  if (!pet) return null;

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

        {showAdoptButton && (
          <button className="adopt-btn" onClick={() => onAdopt(pet.id)}>
            Adopt
          </button>
        )}
      </div>
    </div>
  );
};

export default PetCard;
