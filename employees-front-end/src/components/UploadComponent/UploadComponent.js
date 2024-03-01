// UploadComponent.js

import { useState, useRef } from 'react';
import './UploadComponent.css';
import { useNavigate } from 'react-router-dom';

function UploadComponent() {
  const [selectedFile, setSelectedFile] = useState();
  
  const navigate = useNavigate();

  const handleFileChange = (event) => {
    setSelectedFile(event.target.files[0]);
  };

  const handleSubmit = async () => {
    if (selectedFile) {
      const formData = new FormData();
      formData.append('file', selectedFile);

      fetch('http://localhost:8080/employees', {
        method: 'POST',
        body: formData
      })
      .then(response => response.json())
      .then(data => {
        navigate('/data-grid', {state: {employeeDuoData: data}});
      })
      .catch(() => {
        // Handle error
        console.error('Error uploading file.');
      });
    }
  };

  const fileInputRef = useRef(null);

  const handleClick = (event) => {
    event.preventDefault();
    fileInputRef.current.click();
  };
  
  return (
    <div className="form-container">
      <label htmlFor="file-input" className="form-input" onClick={handleClick}>Select File</label>
      <input ref={fileInputRef} type="file" id="file-input" onChange={handleFileChange} style={{ display: 'none' }} />
      <button className="form-button" onClick={handleSubmit}>Submit</button>
    </div>
  );
}

export default UploadComponent;