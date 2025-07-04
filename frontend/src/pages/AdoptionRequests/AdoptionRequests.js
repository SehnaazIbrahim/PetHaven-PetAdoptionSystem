import React, { useEffect, useState } from 'react';
import {
  getUserRequests,
  getAllRequests,
  approveRequest,
  rejectRequest,
} from '../../services/adoptionService';
import './AdoptionRequests.css';

const AdoptionRequests = () => {
  const [requests, setRequests] = useState([]);
  const role = localStorage.getItem('role');

  const fetchRequests = async () => {
    try {
      const res = role === 'ADMIN' ? await getAllRequests() : await getUserRequests();
      setRequests(res.data);
    } catch (err) {
      console.error('Error fetching adoption requests:', err);
    }
  };

  const handleApprove = async (id) => {
    await approveRequest(id);
    fetchRequests();
  };

  const handleReject = async (id) => {
    await rejectRequest(id);
    fetchRequests();
  };

  useEffect(() => {
    fetchRequests();
  }, []);

  return (
    <div className="requests-page">
      <h2>Adoption Requests</h2>
      {requests.length === 0 ? (
        <p>No requests available.</p>
      ) : (
        <table className="requests-table">
          <thead>
            <tr>
              <th>Pet Name</th>
              <th>User</th>
              <th>Status</th>
              {role === 'ADMIN' && <th>Action</th>}
            </tr>
          </thead>
          <tbody>
            {requests.map((req) => (
              <tr key={req.id}>
                <td>{req.petName}</td>
                <td>{req.userName}</td>
                <td>{req.status}</td>
                {role === 'ADMIN' && (
                  <td>
                    {req.status === 'PENDING' && (
                      <>
                        <button onClick={() => handleApprove(req.id)} className="approve-btn">Approve</button>
                        <button onClick={() => handleReject(req.id)} className="reject-btn">Reject</button>
                      </>
                    )}
                  </td>
                )}
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default AdoptionRequests;
