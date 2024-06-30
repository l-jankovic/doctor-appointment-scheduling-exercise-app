import Axios from "../../apis/Axios"
import {  useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import { Row, Col, Form, Button } from "react-bootstrap";


const CreatePatient=()=>{




    const[createdPatient,setCreatedPatient]=useState({
        name:"",
        surname:"",
        dob:"",
        gender:"",
        town:"",
        doctorId:-1,
        lbo:""

    });
      const [doctors,setDoctors]=useState([]);
     const [sexes,setSexes]=useState([]);
    var navigate = useNavigate();

    
    const getDoctors=()=>{
    Axios.get('/doktori').
    then(res=>{
        console.log(res)
        setDoctors(res.data);

    }).catch(err=>{
        console.log(err);
    })

}

    const getSexes=()=>{
    Axios.get('/pacijenti/polovi').
    then(res=>{
        console.log(res)
        setSexes(res.data);

    }).catch(err=>{
        console.log(err);
    })

}



     useEffect(()=>{
      getDoctors();
      getSexes()
    },[])

 



    const addPatient = () =>{

   
        const dto={
                   ime:createdPatient.name,
                   prezime:createdPatient.surname,
                   datumRodjenja:createdPatient.dob,
                   pol:createdPatient.gender,
                   mesto:createdPatient.town,
                   lbo:createdPatient.lbo,
                   doktorId:createdPatient.doctorId,


        }


        console.log(dto);

        Axios.post('/pacijenti',dto).then(res =>{

        console.log(res);
        navigate("/patients");

    }).catch(err =>{
        console.error("Status kod:", err.response.status);
        console.error("Poruka:", err.response.data);
         console.error("AxiosError:", err);

        alert("Doslo je do greske");
    })

}

  const onValueChange=(e)=>{
        const {name,value}=e.target;




        setCreatedPatient({ ...createdPatient,[name]:value});
        console.log(e.target.value);
    }  


    

         return (
            
            <Row >
                <Col></Col>
                <Col xs="12" sm="10" md="8">
          
                    <Form>
                                <Form.Group>
                            <Form.Label>Name</Form.Label>
                            <Form.Control
                                id="name" name="name" onChange={(e) => onValueChange(e)} /> <br />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Surname</Form.Label>
                            <Form.Control id="surname" name="surname" onChange={(e) => onValueChange(e)} /> <br />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Date of birth</Form.Label>
                            <Form.Control id="dob" name="dob" type="date" onChange={(e) => onValueChange(e)} /> <br />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Town</Form.Label>
                            <Form.Control id="town" name="town" onChange={(e) => onValueChange(e)} /> <br />
                        </Form.Group>
                           <Form.Group>
                            <Form.Label>Gender</Form.Label>
                            <Form.Control as='select' id="gender" name="gender" onChange={(e) => onValueChange(e)} >
                                 <option value={""}>Choose a Gender</option>
                                       {sexes.map((sex, i) => (
                                        <option key={i} value={sex}>{sex}</option>
                                    ))}
                            </Form.Control>
                             <br />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Doctor</Form.Label>
                            <Form.Control as='select' id="doctorId" name="doctorId" onChange={(e) => onValueChange(e)} >
                                 <option value={""}>Choose your doctor</option>
                                       {doctors.map((doctor, i) => (
                                        <option key={doctor.id} value={doctor.id}>{doctor.ime}</option>
                                    ))}
                            </Form.Control>
                             <br />
                        </Form.Group>
                          <Form.Group>
                            <Form.Label>LBO</Form.Label>
                            <Form.Control id="lbo" name="lbo" onChange={(e) => onValueChange(e)} /> <br />
                        </Form.Group>
                           
                    <Button className="btn btn-primary btn-block" onClick={addPatient}>Add</Button>
                    </Form>
                </Col>
                <Col></Col>
          
            </Row>
    )   
}


export default CreatePatient;