import { useNavigate } from "react-router-dom"
import { useEffect, useState,useCallback } from "react"
import { Button, Col, Row, Table,Form } from "react-bootstrap";
import Axios from "../../apis/Axios";
import ShowPatients from "./ShowPatients";
import { Role } from "../../services/auth";


const  Patients = () =>{
    const [pageNo, setPageNo] = useState(0) // state za trenutni broj stranice
    const [maxPages, setMaxPages] = useState(0) // stat
    const [patients,setPatients]=useState([]);
    const [doctors,setDoctors]=useState([]);
     const [searchParams, setSearchParams] = useState({
        
        name: "",
        surname:"",
        doctorId:"",
        lbo:""
    });

     const navigate= useNavigate();


    const getPatients=useCallback(()=>{
        Axios.get(`/pacijenti?pageNo=${pageNo}`,{
             Headers:{'Content-Type': 'application/json'
            },
            data: {},
            params:{
                ime:searchParams.performerId,
                prezime:searchParams.festivalId,
                doktorId:searchParams.doctorId,
                lbo:searchParams.lbo
            }
        }).then(res =>{
            console.log(res)
            setMaxPages(res.headers["total-pages"])
            setPatients(res.data)
        }).catch(err =>{
            console.log(err.data)
        })
    },[searchParams,pageNo])

    


    const getDoctors=()=>{
    Axios.get('/doktori').
    then(res=>{
        console.log(res)
        setDoctors(res.data);

    }).catch(err=>{
        console.log(err);
    })
}



    useEffect(()=>{
     getDoctors()
     getPatients()
    },[pageNo,getPatients]);
    
    




 
      const handleChange=(e)=>{
        const {name,value}=e.target;




        setSearchParams({ ...searchParams,[name]:value});
        console.log(e.target.value);
    } 
/* 
    const handleSearch=()=>{
       getAssignments();
    }
 */
 





const renderPatients = () =>{

    return patients.map((patient,index)=>{
        
        return <ShowPatients key={patient.id}  patient={patient}  ></ShowPatients>
    })
} 

        const goToCreate= ()=>{

        navigate("/patients/create");

    }

return (
      
    <Col>
        < Row><h1>Patients</h1></Row>
                                <Row>
            
                        <div container="container">
                            <Col>
    
                                <Form>
                                                    
                                    <Form.Group>
                                    <Form.Label>Name</Form.Label>
                                        <Form.Control
                                                id="name" name="name" onChange={handleChange} /> <br />
                                        </Form.Group>
                                        <Form.Group>
                                            <Form.Label>Surname</Form.Label>
                                            <Form.Control id="surname" name="surname" onChange={handleChange} /> <br />
                                        </Form.Group>
                                        <Form.Group>
                                    <Form.Label>Doctor</Form.Label>
                                    <Form.Control as='select' id="doctorId" name="doctorId" onChange={handleChange} >
                                        <option value={""}>Choose your doctor</option>
                                            {doctors.map((doctor, i) => (
                                                <option key={doctor.id} value={doctor.id}>{doctor.ime}</option>
                                            ))}
                                    </Form.Control>
                                </Form.Group>
                                        
                                <Form.Group>
                                    <Form.Label>LBO</Form.Label>
                                    <Form.Control id="lbo" name="lbo"  onChange={handleChange} /> <br />
                                </Form.Group>
                                </Form>
                                
                            </Col>
                        </div>
                    </Row>      

                              <br/> <br/> <br/>
                    
               
  
               <Row><Col> 
                <div style={{ display: 'flex', justifyContent: "space-between",marginBottom:'5px' }}>

                        <div>
                        {Role() === 'isAdmin' &&
                            <Button variant="success" onClick={goToCreate} >Create Patient</Button>
                        }
                    </div>
                    <div>
                        <Button disabled={pageNo <= 0} onClick={() => setPageNo(pageNo - 1)} className="btn btn-info" style={{ color: 'white' }}>Previous</Button>
                        <Button disabled={maxPages === -1 || pageNo >= maxPages - 1} onClick={() => setPageNo(pageNo + 1)} className="btn btn-info" style={{ color: 'white', marginLeft: '4px' }}>Next</Button>
                    </div>


                </div>            
                <Table id="assigment-table"className="table-striped table-bordered table-hover"  >
                   <thead className="table-dark" >
                        <tr >
                            <th>Name</th>
                            <th>Surname</th>
                            <th>Date of birth</th>
                            <th>Town</th>
                            <th>Gender</th>
                            <th>Doctor</th>
                            <th>LBO</th>
                            {Role()=='isUser'&&
                            <th></th>}
                             {Role()=='isAdmin'&&
                            <th></th>}
                         
                        </tr>
                    </thead>
                    <tbody >
                        {renderPatients()}
                    </tbody>                  
                </Table>
                </Col></Row>
                
               
              </Col>
       
    )








}



export default Patients;