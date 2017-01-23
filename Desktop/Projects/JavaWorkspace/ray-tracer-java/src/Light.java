
public class Light {

	private Vector3f pos;
	private Vector3f specular;
	private Vector3f diffuse;
	
	public Light(Vector3f pos, Vector3f specular, Vector3f diffuse){
		
		this.pos = pos;
		this.specular = specular;
		this.diffuse = diffuse;
		
	}
	
	public Vector3f getPos(){ return this.pos; }
	public Vector3f getSpecular(){ return this.specular; }
	public Vector3f getDiffuse(){ return this.diffuse; }

	@Override
	public String toString() {
		return "Light [pos=" + pos + ", specular=" + specular + ", diffuse=" + diffuse + "]";
	}
	
	
	
}
