export interface Patient {
    id: string,
    firstName: string,
    lastName: string,
    height: number,
    weight: number,
    ssn: string,
    isDiagnosed: boolean,
    isCurrentlyInHospital: boolean,
    stationId?: string
    dob: string
}
