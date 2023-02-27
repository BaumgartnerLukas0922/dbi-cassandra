export interface Diagnosis {
    id: string,
    medicalStaffId: string,
    daysInHospital: number,
    conditionId: string,
    patientId: string,
    diagnosedOn: Date
}
