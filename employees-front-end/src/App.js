import { Routes, Route, Navigate } from "react-router-dom"
import './App.css';
import UploadComponent from './components/UploadComponent/UploadComponent';
import DataGridComponent from './components/DataGridComponent/DataGridComponent';

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/data-grid" element={ <DataGridComponent/> } />
        <Route path="/upload" element={ <UploadComponent/> } />
        <Route path="/" element={<Navigate to="/upload" replace />} />
      </Routes>
    </div>
  );
}

export default App;
