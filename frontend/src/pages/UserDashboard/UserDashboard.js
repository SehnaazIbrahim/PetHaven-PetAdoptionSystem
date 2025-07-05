import React, { useEffect, useState } from 'react';
import { getUserProfile } from '../../services/userService';
import { getUserAdoptionRequests } from '../../services/userService'; // ✅ updated
import PetCard from '../../components/PetCard';
import './UserDashboard.css';

const UserDashboard = () => {
  const [profile, setProfile] = useState({});
  const [adoptionRequests, setAdoptionRequests] = useState([]);
  const [adoptedPets, setAdoptedPets] = useState([]);

  useEffect(() => {
    const fetchUserDetails = async () => {
      try {
        // ✅ Get user profile (with name, email, etc.)
        const profileRes = await getUserProfile();
        const userData = profileRes.data;
        setProfile(userData);

        // ✅ Get all requests made by this user
        const requestsRes = await getUserAdoptionRequests();
        const allRequests = requestsRes.data;
        setAdoptionRequests(allRequests);

        // ✅ Filter adopted pets from those requests
        const adopted = allRequests
          .filter(req => req.pet?.status === 'Adopted')
          .map(req => req.pet);
        setAdoptedPets(adopted);

      } catch (err) {
        console.error('Error fetching dashboard data:', err);
      }
    };

    fetchUserDetails();
  }, []);

  return (
    <div className="user-dashboard">
      <h2>Welcome, {profile.name || 'User'}!</h2>
      <p>Email: {profile.email || 'Loading...'}</p>

      {/* 🐶 Adopted Pets Section */}
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

      {/* 📄 Adoption Requests Section */}
      <h3>Your Adoption Requests</h3>
      {adoptionRequests.length === 0 ? (
        <p>No requests made yet.</p>
      ) : (
        <table className="request-table">
          <thead>
            <tr>
              <th>Pet Name</th>
              <th>Requested On</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            {adoptionRequests.map((req, index) => (
              <tr key={index}>
                <td>{req.petName || 'N/A'}</td>
                <td>{req.requestDate || 'N/A'}</td>
                <td>{req.status}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default UserDashboard;
