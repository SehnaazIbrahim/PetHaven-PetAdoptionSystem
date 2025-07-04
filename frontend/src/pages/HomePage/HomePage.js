import React, { useEffect, useState } from 'react';
import { getAllPets, adoptPet } from '../../services/petService';
import PetCard from '../../components/PetCard';
import './HomePage.css';

const HomePage = () => {
  const [pets, setPets] = useState([]);

  const fetchPets = async () => {
    try {
      const res = await getAllPets();
      setPets(res.data);
    } catch (err) {
      console.error('Error fetching pets:', err);
    }
  };

  const handleAdopt = async (petId) => {
    try {
      await adoptPet(petId);
      alert('Adoption request sent!');
    } catch (err) {
      alert('You must be logged in to adopt.');
    }
  };

  useEffect(() => {
    fetchPets();
  }, []);

  return (
    <div className="home-page">
      <div className="hero-section">
        <h1 className='home-head'>Adopt a Pet Today</h1>
        <p className='home-head'>Because every animal deserves a loving home ❤️</p>
      </div>

      <div className="pet-list">
        {pets.length === 0 ? (
          <p>No pets available right now.</p>
        ) : (
          pets.map((pet) => (
            <PetCard key={pet.id} pet={pet} onAdopt={handleAdopt} />
          ))
        )}
      </div>
    </div>
  );
};

export default HomePage;
