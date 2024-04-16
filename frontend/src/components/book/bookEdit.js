import React from 'react';
// import {useHistory} from 'react-router-dom';
import {useNavigate} from 'react-router-dom';

const BookEdit = (props) => {

    // const history = useHistory();
    const navigate = useNavigate()
    const [formData, updateFormData] = React.useState({
        name: "",
        category: props.categories[0],
        author: 0,
        availableCopies: 0
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const name = formData.name !== "" ? formData.name : props.book.name;
        const author = formData.author !== 0 ? formData.author : props.book.author.id;
        const category = formData.category !== "" ? formData.category : props.book.category;
        const availableCopies = formData.availableCopies !== 0 ? formData.availableCopies : props.book.availableCopies;

        props.onEditBook(props.book.id, name, category, author, availableCopies);
        // history.push("/products");
        navigate('/books');
    }

    return (
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="name">book name</label>
                        <input type="text"
                               className="form-control"
                               id="name"
                               name="name"
                               placeholder={props.book.name}
                               onChange={handleChange}
                        />
                    </div>


                    <div className="form-group">
                        <label>Category</label>
                        <select name="category" className="form-control" onChange={handleChange}>
                            {props.categories.map((term) => {
                                    if (props.book.category !== undefined)
                                        return <option selected={props.book.name}
                                                       value={term.name}>{term.toString()}</option>
                                    else return <option value={term.name}>{term.toString()}</option>
                                }
                                // <option value={term.name}>{term.toString()}</option>
                            )}
                        </select>
                    </div>

                    <div className="form-group">
                        <label>author</label>
                        <select name="author" className="form-control" onChange={handleChange}>
                            {props.authors.map((term) => {
                                if (props.book.author !== undefined &&
                                    props.book.author.id === term.id)
                                    return <option selected={props.book.author.id}
                                                   value={term.id}>{term.name}</option>
                                else return <option value={term.id}>{term.name}</option>
                            })}
                        </select>
                    </div>
                    <div className="form-group">
                        <label htmlFor="availableCopies">kopii</label>
                        <input type="text"
                               className="form-control"
                               id="availableCopies"
                               name="availableCopies"
                               placeholder={props.book.availableCopies}
                               onChange={handleChange}
                        />
                    </div>

                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    )
}

export default BookEdit;
