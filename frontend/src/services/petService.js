import axios from 'axios';

const API_URL = 'http://localhost:8080/pets';

export const getAllPets = () => axios.get(API_URL);

export const getPetById = (id) => axios.get(`${API_URL}/${id}`);

export const addNewPet = (pet) => axios.post(API_URL, pet);

export const updatePet = (id, pet) => axios.put(`${API_URL}/${id}`, pet);

export const deletePet = (id) => axios.delete(`${API_URL}/${id}`);


export const adoptPet = (petId) => {
  const user = JSON.parse(localStorage.getItem('user'));
  const email = user?.email;

  if (!email) {
    throw new Error('User email not found in localStorage.');
  }

  return axios.post(`http://localhost:8080/pets/adopt/${petId}`, null, {
    params: { userEmail: email }
  });
};

