import axios from 'axios';

const API_URL = 'http://localhost:8080/pets';

export const getAllPets = () => axios.get(API_URL);

export const getPetById = (id) => axios.get(`${API_URL}/${id}`);

export const addNewPet = (pet) => axios.post(API_URL, pet);

export const updatePet = (id, pet) => axios.put(`${API_URL}/${id}`, pet);

export const deletePet = (id) => axios.delete(`${API_URL}/${id}`);


export const adoptPet = (petId) => {
  const token = localStorage.getItem('token');
  return axios.post(`${API_URL}/adopt/${petId}`, {}, {
    headers: {
      Authorization: `Bearer ${token}`
    }
  });
};
