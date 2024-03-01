// DataGridComponent.js
import { useLocation, Link } from 'react-router-dom';
import './DataGridComponent.css';

function DataGridComponent() {
    const location = useLocation();
    console.log(location.state.employeeDuoData)

    const DataGrid = location.state.employeeDuoData
    
    return (
      <div className="App">
      {DataGrid.errorMessage && <div className="error-message">{DataGrid.errorMessage}</div>}
        {!DataGrid.errorMessage && 
        <table>
          <thead>
            <tr>
              <th>Employee ID #1</th>
              <th>Employee ID #2</th>
              <th>Project ID</th>
              <th>Days Worked</th>
            </tr>
          </thead>
          <tbody>
          {DataGrid.collaborations.map(collab => (
            <tr>
              <td>{DataGrid.employees.firstEmployeeID}</td>
              <td>{DataGrid.employees.secondEmployeeID}</td>
              <td>{collab.projectID}</td>
              <td>{collab.daysWorked}</td>
            </tr>
          ))}
          </tbody>
        </table>}
        <br/>
        <Link to="/upload" className="form-button">Back to Upload</Link>
      </div>
    );
  }
  
  export default DataGridComponent;