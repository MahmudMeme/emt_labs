import React from 'react';
// import {useHistory} from 'react-router-dom';
import {useNavigate} from 'react-router-dom';

const BookAdd = (props) => {

    // const history = useHistory();
    const navigate = useNavigate();
    const [formData, updateFormData] = React.useState({
        name: "",
        author: 1,
        category: props.categories[0],
        availableCopies: 1
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const name = formData.name;
        const author = formData.author;
        const category = formData.category;
        const availableCopies = formData.availableCopies;

        props.onAddProduct(name, author, category, availableCopies);
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
                               required
                               placeholder="Enter book name"
                               onChange={handleChange}
                        />
                    </div>
                    {/*<div className="form-group">*/}
                    {/*    <label htmlFor="category">category</label>*/}
                    {/*    <input type="text"*/}
                    {/*           className="form-control"*/}
                    {/*           id="category"*/}
                    {/*           name="category"*/}
                    {/*           placeholder="categry"*/}
                    {/*           required*/}
                    {/*           onChange={handleChange}*/}
                    {/*    />*/}
                    {/*</div>*/}

                    <div className="form-group">
                        <label>Category</label>
                        <select name="category" className="form-control" onChange={handleChange}>
                            {props.categories.map((term) =>
                                <option value={term.name}>{term.toString()}</option>
                            )}
                        </select>
                    </div>

                    {/*<div className="form-group">*/}
                    {/*    <label htmlFor="category">Category</label>*/}
                    {/*    <select name="category" id="category" className="form-control" onChange={handleChange}>*/}
                    {/*        <option value="">Select Category</option>*/}
                    {/*        {props.categories.map((category) => (*/}
                    {/*            <option key={category.id} value={category.name}>*/}
                    {/*                {category.name}*/}
                    {/*            </option>*/}
                    {/*        ))}*/}
                    {/*    </select>*/}
                    {/*</div>*/}

                    <div className="form-group">
                        <label>author</label>
                        <select name="author" className="form-control" onChange={handleChange}>
                            {props.authors.map((term) =>
                                <option value={term.id}>{term.name}</option>
                            )}
                        </select>
                    </div>
                    <div className="form-group">
                        <label htmlFor="availableCopies">kopii</label>
                        <input type="text"
                               className="form-control"
                               id="availableCopies"
                               name="availableCopies"
                               placeholder="kopii"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    )
}

export default BookAdd;
