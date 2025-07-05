import React, { useEffect, useState } from 'react';
import { getAllPets, adoptPet } from '../../services/petService';
import PetCard from '../../components/PetCard';
import './HomePage.css';

const HomePage = () => {
  const [pets, setPets] = useState([]);

  const fetchPets = async () => {
    try {
      const res = await getAllPets();
      const petList = Array.isArray(res.data) ? res.data : [];
      setPets(petList);
    } catch (err) {
      console.error('Error fetching pets:', err);
    }
  };

  const handleAdopt = async (petId) => {
    try {
      await adoptPet(petId);
      alert('Adoption request sent!');
      fetchPets();
    } catch (err) {
      console.error(err);
      alert('You must be logged in to adopt.');
    }
  };

  useEffect(() => {
    fetchPets();
  }, []);

  return (
    <div className="home-page">
      <div className="hero-section">
        <h1 className="home-head">Adopt a Pet Today</h1>
        <p className="home-head">Because every animal deserves a loving home ❤️</p>
      </div>

      <div className="pet-list">
        {Array.isArray(pets) && pets.length > 0 ? (
          pets.map((pet) => (
            <PetCard
              key={pet.id}
              pet={pet}
              onAdopt={handleAdopt}
              showAdoptButton={pet.status !== 'Adopted'}
            />
          ))
        ) : (
          <p>No pets available right now.</p>
        )}
      </div>
    </div>
  );
};

export default HomePage;
