import React, { useEffect, useState } from 'react';
import { getUserProfile } from '../../services/userService';
import PetCard from '../../components/PetCard';
import './UserDashboard.css';

const UserDashboard = () => {
  const [profile, setProfile] = useState({});
  const [adoptedPets, setAdoptedPets] = useState([]);

  useEffect(() => {
    const fetchUser = async () => {
      try {
        const res = await getUserProfile();
        setProfile(res.data);
        setAdoptedPets(res.data.adoptedPets || []);
      } catch (err) {
        console.error('User not found or unauthorized', err);
      }
    };

    fetchUser();
  }, []);

  return (
    <div className="user-dashboard">
      <h2>Welcome, {profile.name || "User"}!</h2>
      <p>Email: {profile.email || "Loading..."}</p>

      <h3>Your Adopted Pets</h3>
      <div className="dashboard-pet-list">
        {adoptedPets.length === 0 ? (
          <p>No pets adopted yet 🐾</p>
        ) : (
          adoptedPets.map(pet => (
            <PetCard key={pet.id} pet={pet} showAdoptButton={false} />
          ))
        )}
      </div>
    </div>
  );
};

export default UserDashboard;
